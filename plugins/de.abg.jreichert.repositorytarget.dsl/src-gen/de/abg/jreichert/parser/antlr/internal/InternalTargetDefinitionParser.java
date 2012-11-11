package de.abg.jreichert.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.abg.jreichert.services.TargetDefinitionGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalTargetDefinitionParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_URL", "RULE_VERSION", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'TargetDefinition'", "'targetFile'", "'='", "'{'", "','", "'}'", "'noFeature'"
    };
    public static final int RULE_VERSION=7;
    public static final int RULE_ID=5;
    public static final int RULE_STRING=4;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int RULE_ANY_OTHER=11;
    public static final int RULE_WS=10;
    public static final int RULE_SL_COMMENT=9;
    public static final int EOF=-1;
    public static final int RULE_URL=6;
    public static final int RULE_ML_COMMENT=8;

    // delegates
    // delegators


        public InternalTargetDefinitionParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalTargetDefinitionParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalTargetDefinitionParser.tokenNames; }
    public String getGrammarFileName() { return "../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g"; }



     	private TargetDefinitionGrammarAccess grammarAccess;
     	
        public InternalTargetDefinitionParser(TokenStream input, TargetDefinitionGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Target";	
       	}
       	
       	@Override
       	protected TargetDefinitionGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleTarget"
    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:67:1: entryRuleTarget returns [EObject current=null] : iv_ruleTarget= ruleTarget EOF ;
    public final EObject entryRuleTarget() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTarget = null;


        try {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:68:2: (iv_ruleTarget= ruleTarget EOF )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:69:2: iv_ruleTarget= ruleTarget EOF
            {
             newCompositeNode(grammarAccess.getTargetRule()); 
            pushFollow(FOLLOW_ruleTarget_in_entryRuleTarget75);
            iv_ruleTarget=ruleTarget();

            state._fsp--;

             current =iv_ruleTarget; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTarget85); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTarget"


    // $ANTLR start "ruleTarget"
    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:76:1: ruleTarget returns [EObject current=null] : (otherlv_0= 'TargetDefinition' ( (lv_name_1_0= RULE_STRING ) ) (otherlv_2= 'targetFile' otherlv_3= '=' ( (lv_targetFileName_4_0= RULE_ID ) ) )? ( (lv_locations_5_0= ruleLocation ) )* ) ;
    public final EObject ruleTarget() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_targetFileName_4_0=null;
        EObject lv_locations_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:79:28: ( (otherlv_0= 'TargetDefinition' ( (lv_name_1_0= RULE_STRING ) ) (otherlv_2= 'targetFile' otherlv_3= '=' ( (lv_targetFileName_4_0= RULE_ID ) ) )? ( (lv_locations_5_0= ruleLocation ) )* ) )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:80:1: (otherlv_0= 'TargetDefinition' ( (lv_name_1_0= RULE_STRING ) ) (otherlv_2= 'targetFile' otherlv_3= '=' ( (lv_targetFileName_4_0= RULE_ID ) ) )? ( (lv_locations_5_0= ruleLocation ) )* )
            {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:80:1: (otherlv_0= 'TargetDefinition' ( (lv_name_1_0= RULE_STRING ) ) (otherlv_2= 'targetFile' otherlv_3= '=' ( (lv_targetFileName_4_0= RULE_ID ) ) )? ( (lv_locations_5_0= ruleLocation ) )* )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:80:3: otherlv_0= 'TargetDefinition' ( (lv_name_1_0= RULE_STRING ) ) (otherlv_2= 'targetFile' otherlv_3= '=' ( (lv_targetFileName_4_0= RULE_ID ) ) )? ( (lv_locations_5_0= ruleLocation ) )*
            {
            otherlv_0=(Token)match(input,12,FOLLOW_12_in_ruleTarget122); 

                	newLeafNode(otherlv_0, grammarAccess.getTargetAccess().getTargetDefinitionKeyword_0());
                
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:84:1: ( (lv_name_1_0= RULE_STRING ) )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:85:1: (lv_name_1_0= RULE_STRING )
            {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:85:1: (lv_name_1_0= RULE_STRING )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:86:3: lv_name_1_0= RULE_STRING
            {
            lv_name_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTarget139); 

            			newLeafNode(lv_name_1_0, grammarAccess.getTargetAccess().getNameSTRINGTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getTargetRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"STRING");
            	    

            }


            }

            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:102:2: (otherlv_2= 'targetFile' otherlv_3= '=' ( (lv_targetFileName_4_0= RULE_ID ) ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==13) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:102:4: otherlv_2= 'targetFile' otherlv_3= '=' ( (lv_targetFileName_4_0= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,13,FOLLOW_13_in_ruleTarget157); 

                        	newLeafNode(otherlv_2, grammarAccess.getTargetAccess().getTargetFileKeyword_2_0());
                        
                    otherlv_3=(Token)match(input,14,FOLLOW_14_in_ruleTarget169); 

                        	newLeafNode(otherlv_3, grammarAccess.getTargetAccess().getEqualsSignKeyword_2_1());
                        
                    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:110:1: ( (lv_targetFileName_4_0= RULE_ID ) )
                    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:111:1: (lv_targetFileName_4_0= RULE_ID )
                    {
                    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:111:1: (lv_targetFileName_4_0= RULE_ID )
                    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:112:3: lv_targetFileName_4_0= RULE_ID
                    {
                    lv_targetFileName_4_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTarget186); 

                    			newLeafNode(lv_targetFileName_4_0, grammarAccess.getTargetAccess().getTargetFileNameIDTerminalRuleCall_2_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTargetRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"targetFileName",
                            		lv_targetFileName_4_0, 
                            		"ID");
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:128:4: ( (lv_locations_5_0= ruleLocation ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_STRING||LA2_0==RULE_URL) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:129:1: (lv_locations_5_0= ruleLocation )
            	    {
            	    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:129:1: (lv_locations_5_0= ruleLocation )
            	    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:130:3: lv_locations_5_0= ruleLocation
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getTargetAccess().getLocationsLocationParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleLocation_in_ruleTarget214);
            	    lv_locations_5_0=ruleLocation();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getTargetRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"locations",
            	            		lv_locations_5_0, 
            	            		"Location");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTarget"


    // $ANTLR start "entryRuleLocation"
    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:154:1: entryRuleLocation returns [EObject current=null] : iv_ruleLocation= ruleLocation EOF ;
    public final EObject entryRuleLocation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocation = null;


        try {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:155:2: (iv_ruleLocation= ruleLocation EOF )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:156:2: iv_ruleLocation= ruleLocation EOF
            {
             newCompositeNode(grammarAccess.getLocationRule()); 
            pushFollow(FOLLOW_ruleLocation_in_entryRuleLocation251);
            iv_ruleLocation=ruleLocation();

            state._fsp--;

             current =iv_ruleLocation; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocation261); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLocation"


    // $ANTLR start "ruleLocation"
    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:163:1: ruleLocation returns [EObject current=null] : ( ( (lv_name_0_0= RULE_STRING ) )? ( (lv_repositoryLocation_1_0= RULE_URL ) ) otherlv_2= '{' ( (lv_unit_3_0= ruleUnit ) ) (otherlv_4= ',' ( (lv_unit_5_0= ruleUnit ) ) )* otherlv_6= '}' ) ;
    public final EObject ruleLocation() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token lv_repositoryLocation_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_unit_3_0 = null;

        EObject lv_unit_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:166:28: ( ( ( (lv_name_0_0= RULE_STRING ) )? ( (lv_repositoryLocation_1_0= RULE_URL ) ) otherlv_2= '{' ( (lv_unit_3_0= ruleUnit ) ) (otherlv_4= ',' ( (lv_unit_5_0= ruleUnit ) ) )* otherlv_6= '}' ) )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:167:1: ( ( (lv_name_0_0= RULE_STRING ) )? ( (lv_repositoryLocation_1_0= RULE_URL ) ) otherlv_2= '{' ( (lv_unit_3_0= ruleUnit ) ) (otherlv_4= ',' ( (lv_unit_5_0= ruleUnit ) ) )* otherlv_6= '}' )
            {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:167:1: ( ( (lv_name_0_0= RULE_STRING ) )? ( (lv_repositoryLocation_1_0= RULE_URL ) ) otherlv_2= '{' ( (lv_unit_3_0= ruleUnit ) ) (otherlv_4= ',' ( (lv_unit_5_0= ruleUnit ) ) )* otherlv_6= '}' )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:167:2: ( (lv_name_0_0= RULE_STRING ) )? ( (lv_repositoryLocation_1_0= RULE_URL ) ) otherlv_2= '{' ( (lv_unit_3_0= ruleUnit ) ) (otherlv_4= ',' ( (lv_unit_5_0= ruleUnit ) ) )* otherlv_6= '}'
            {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:167:2: ( (lv_name_0_0= RULE_STRING ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_STRING) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:168:1: (lv_name_0_0= RULE_STRING )
                    {
                    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:168:1: (lv_name_0_0= RULE_STRING )
                    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:169:3: lv_name_0_0= RULE_STRING
                    {
                    lv_name_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLocation303); 

                    			newLeafNode(lv_name_0_0, grammarAccess.getLocationAccess().getNameSTRINGTerminalRuleCall_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLocationRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_0_0, 
                            		"STRING");
                    	    

                    }


                    }
                    break;

            }

            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:185:3: ( (lv_repositoryLocation_1_0= RULE_URL ) )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:186:1: (lv_repositoryLocation_1_0= RULE_URL )
            {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:186:1: (lv_repositoryLocation_1_0= RULE_URL )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:187:3: lv_repositoryLocation_1_0= RULE_URL
            {
            lv_repositoryLocation_1_0=(Token)match(input,RULE_URL,FOLLOW_RULE_URL_in_ruleLocation326); 

            			newLeafNode(lv_repositoryLocation_1_0, grammarAccess.getLocationAccess().getRepositoryLocationURLTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLocationRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"repositoryLocation",
                    		lv_repositoryLocation_1_0, 
                    		"URL");
            	    

            }


            }

            otherlv_2=(Token)match(input,15,FOLLOW_15_in_ruleLocation343); 

                	newLeafNode(otherlv_2, grammarAccess.getLocationAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:207:1: ( (lv_unit_3_0= ruleUnit ) )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:208:1: (lv_unit_3_0= ruleUnit )
            {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:208:1: (lv_unit_3_0= ruleUnit )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:209:3: lv_unit_3_0= ruleUnit
            {
             
            	        newCompositeNode(grammarAccess.getLocationAccess().getUnitUnitParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleUnit_in_ruleLocation364);
            lv_unit_3_0=ruleUnit();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getLocationRule());
            	        }
                   		add(
                   			current, 
                   			"unit",
                    		lv_unit_3_0, 
                    		"Unit");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:225:2: (otherlv_4= ',' ( (lv_unit_5_0= ruleUnit ) ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==16) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:225:4: otherlv_4= ',' ( (lv_unit_5_0= ruleUnit ) )
            	    {
            	    otherlv_4=(Token)match(input,16,FOLLOW_16_in_ruleLocation377); 

            	        	newLeafNode(otherlv_4, grammarAccess.getLocationAccess().getCommaKeyword_4_0());
            	        
            	    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:229:1: ( (lv_unit_5_0= ruleUnit ) )
            	    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:230:1: (lv_unit_5_0= ruleUnit )
            	    {
            	    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:230:1: (lv_unit_5_0= ruleUnit )
            	    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:231:3: lv_unit_5_0= ruleUnit
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getLocationAccess().getUnitUnitParserRuleCall_4_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleUnit_in_ruleLocation398);
            	    lv_unit_5_0=ruleUnit();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getLocationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"unit",
            	            		lv_unit_5_0, 
            	            		"Unit");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            otherlv_6=(Token)match(input,17,FOLLOW_17_in_ruleLocation412); 

                	newLeafNode(otherlv_6, grammarAccess.getLocationAccess().getRightCurlyBracketKeyword_5());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLocation"


    // $ANTLR start "entryRuleUnit"
    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:259:1: entryRuleUnit returns [EObject current=null] : iv_ruleUnit= ruleUnit EOF ;
    public final EObject entryRuleUnit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnit = null;


        try {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:260:2: (iv_ruleUnit= ruleUnit EOF )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:261:2: iv_ruleUnit= ruleUnit EOF
            {
             newCompositeNode(grammarAccess.getUnitRule()); 
            pushFollow(FOLLOW_ruleUnit_in_entryRuleUnit448);
            iv_ruleUnit=ruleUnit();

            state._fsp--;

             current =iv_ruleUnit; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnit458); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnit"


    // $ANTLR start "ruleUnit"
    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:268:1: ruleUnit returns [EObject current=null] : ( ( (lv_categoryId_0_0= RULE_ID ) ) ( (lv_version_1_0= RULE_VERSION ) )? ( (lv_noFeature_2_0= 'noFeature' ) )? ) ;
    public final EObject ruleUnit() throws RecognitionException {
        EObject current = null;

        Token lv_categoryId_0_0=null;
        Token lv_version_1_0=null;
        Token lv_noFeature_2_0=null;

         enterRule(); 
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:271:28: ( ( ( (lv_categoryId_0_0= RULE_ID ) ) ( (lv_version_1_0= RULE_VERSION ) )? ( (lv_noFeature_2_0= 'noFeature' ) )? ) )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:272:1: ( ( (lv_categoryId_0_0= RULE_ID ) ) ( (lv_version_1_0= RULE_VERSION ) )? ( (lv_noFeature_2_0= 'noFeature' ) )? )
            {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:272:1: ( ( (lv_categoryId_0_0= RULE_ID ) ) ( (lv_version_1_0= RULE_VERSION ) )? ( (lv_noFeature_2_0= 'noFeature' ) )? )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:272:2: ( (lv_categoryId_0_0= RULE_ID ) ) ( (lv_version_1_0= RULE_VERSION ) )? ( (lv_noFeature_2_0= 'noFeature' ) )?
            {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:272:2: ( (lv_categoryId_0_0= RULE_ID ) )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:273:1: (lv_categoryId_0_0= RULE_ID )
            {
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:273:1: (lv_categoryId_0_0= RULE_ID )
            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:274:3: lv_categoryId_0_0= RULE_ID
            {
            lv_categoryId_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleUnit500); 

            			newLeafNode(lv_categoryId_0_0, grammarAccess.getUnitAccess().getCategoryIdIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getUnitRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"categoryId",
                    		lv_categoryId_0_0, 
                    		"ID");
            	    

            }


            }

            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:290:2: ( (lv_version_1_0= RULE_VERSION ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_VERSION) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:291:1: (lv_version_1_0= RULE_VERSION )
                    {
                    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:291:1: (lv_version_1_0= RULE_VERSION )
                    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:292:3: lv_version_1_0= RULE_VERSION
                    {
                    lv_version_1_0=(Token)match(input,RULE_VERSION,FOLLOW_RULE_VERSION_in_ruleUnit522); 

                    			newLeafNode(lv_version_1_0, grammarAccess.getUnitAccess().getVersionVERSIONTerminalRuleCall_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getUnitRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"version",
                            		lv_version_1_0, 
                            		"VERSION");
                    	    

                    }


                    }
                    break;

            }

            // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:308:3: ( (lv_noFeature_2_0= 'noFeature' ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==18) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:309:1: (lv_noFeature_2_0= 'noFeature' )
                    {
                    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:309:1: (lv_noFeature_2_0= 'noFeature' )
                    // ../de.abg.jreichert.repositorytarget.dsl/src-gen/de/abg/jreichert/parser/antlr/internal/InternalTargetDefinition.g:310:3: lv_noFeature_2_0= 'noFeature'
                    {
                    lv_noFeature_2_0=(Token)match(input,18,FOLLOW_18_in_ruleUnit546); 

                            newLeafNode(lv_noFeature_2_0, grammarAccess.getUnitAccess().getNoFeatureNoFeatureKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getUnitRule());
                    	        }
                           		setWithLastConsumed(current, "noFeature", true, "noFeature");
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnit"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleTarget_in_entryRuleTarget75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTarget85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleTarget122 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTarget139 = new BitSet(new long[]{0x0000000000002052L});
    public static final BitSet FOLLOW_13_in_ruleTarget157 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleTarget169 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTarget186 = new BitSet(new long[]{0x0000000000000052L});
    public static final BitSet FOLLOW_ruleLocation_in_ruleTarget214 = new BitSet(new long[]{0x0000000000000052L});
    public static final BitSet FOLLOW_ruleLocation_in_entryRuleLocation251 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocation261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLocation303 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_URL_in_ruleLocation326 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleLocation343 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleUnit_in_ruleLocation364 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_16_in_ruleLocation377 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleUnit_in_ruleLocation398 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_17_in_ruleLocation412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnit_in_entryRuleUnit448 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnit458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleUnit500 = new BitSet(new long[]{0x0000000000040082L});
    public static final BitSet FOLLOW_RULE_VERSION_in_ruleUnit522 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_ruleUnit546 = new BitSet(new long[]{0x0000000000000002L});

}