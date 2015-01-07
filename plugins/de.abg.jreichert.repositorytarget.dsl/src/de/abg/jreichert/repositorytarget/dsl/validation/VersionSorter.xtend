package de.abg.jreichert.repositorytarget.dsl.validation

import java.util.List

class VersionSorter {

   def sortVersions(Iterable<String> versions) {
      val tokenized = versions.map[it -> it.tokenize.toList].toList
      tokenized.sort(left, right| compareVersions(left.value, right.value))
      tokenized.map[key]
   }

   def tokenize(String version) {
      version.tokenize('\\.').map[it.tokenize('-')].flatten
   }

   def tokenize(String version, String delimiter) {
      version.split(delimiter).toList.fold(newArrayList, [list, b | if(!list.empty) list.add(delimiter); list.add(b); list])
   }
   
   def compareVersions(List<String> left, List<String> right) {
      val max = if (left.size < right.size) left.size else right.size
      val typeList = <Class<?>>newArrayList
      for(i : 0 ..< max) {
         if(parse(left.get(i)) instanceof Integer && parse(right.get(i)) instanceof Integer) {
            typeList.add(Integer)
         } else {
            typeList.add(String)
         }
      }
      if(typeList.size > 0) {
         var oldType = null
         val newLeftList = newArrayList
         val newRightList = newArrayList
         var newLeftString = ""
         var newRightString = ""
         for(i : 0 ..< typeList.size) {
            if(typeList.get(i) == oldType == String) {
               newLeftString += left.get(i) 
               newRightString += right.get(i) 
            } else {
               newLeftList.add(newLeftString)
               newLeftString = ""
               newRightList.add(newRightString)
               newRightString = ""
               newLeftList.add(left.get(i))
               newRightList.add(right.get(i))
            }
         }
         newLeftString = ""
         newRightString = ""
         for(i : typeList.size ..< left.size) {
            newLeftString += left.get(i) 
         }
         newLeftList.add(newLeftString)
         for(i : typeList.size ..< right.size) {
            newRightString += right.get(i) 
         }
         newRightList.add(newRightString)
         val compareResult = (0..<newLeftList.size).map[i|newLeftList.get(i).parse.compareValue(newRightList.get(i).parse)]
         val findFirst = compareResult.findFirst[it != 0]
         return if(findFirst === null) 0 else findFirst
      }
      return 0
   }
   
   def Object parse(String s) {
      try {
         return Integer.parseInt(s);
      } catch(NumberFormatException e) {
         return s
      }
   }
   
   def dispatch int compareValue(String s, String s2) {
      s.compareTo(s2)
   }

   def dispatch int compareValue(String s, Integer i2) {
      -compareValue(i2, s)
   }

   def dispatch int compareValue(Integer i, String s2) {
      if(s2.length == 0) {
         -1
      } else {
         val iAsString = Integer.toString(i)
         if(iAsString.length == s2.length) {
            return iAsString.compareTo(s2)
         } else {
            if(iAsString.length > s2.length) {
               val result = iAsString.substring(0, iAsString.length-s2.length).compareTo(s2)
               if(result == 0) {
                  return compareValue(iAsString.substring(iAsString.length-s2.length), "")
               } else {
                  return -result
               }
            } else {
               val result = iAsString.compareTo(s2.substring(0, s2.length-iAsString.length))
               if(result == 0) {
                  return compareValue(s2.substring(s2.length-iAsString.length), "")
               } else {
                  return -result
               }
            }
         }
      }
   }
   
   def dispatch int compareValue(Integer i, Integer i2) {
      i.compareTo(i2)
   }

   def dispatch int compareValue(Object o, Object o2) {
      o.toString.compareTo(o2.toString)
   }     
}