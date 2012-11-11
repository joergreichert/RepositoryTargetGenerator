package de.abg.jreichert.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import de.abg.jreichert.services.TargetDefinitionGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalTargetDefinitionParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_URL", "RULE_VERSION", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'TargetDefinition'", "'targetFile'", "'='", "'{'", "'}'", "','", "'noFeature'"
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
    public String getGrammarFileName() { return "../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g"; }


     
     	private TargetDefinitionGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(TargetDefinitionGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }
        
        @Override
        protected String getValueForTokenName(String tokenName) {
        	return tokenName;
        }




    // $ANTLR start "entryRuleTarget"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:60:1: entryRuleTarget : ruleTarget EOF ;
    public final void entryRuleTarget() throws RecognitionException {
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:61:1: ( ruleTarget EOF )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:62:1: ruleTarget EOF
            {
             before(grammarAccess.getTargetRule()); 
            pushFollow(FOLLOW_ruleTarget_in_entryRuleTarget61);
            ruleTarget();

            state._fsp--;

             after(grammarAccess.getTargetRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTarget68); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleTarget"


    // $ANTLR start "ruleTarget"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:69:1: ruleTarget : ( ( rule__Target__Group__0 ) ) ;
    public final void ruleTarget() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:73:2: ( ( ( rule__Target__Group__0 ) ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:74:1: ( ( rule__Target__Group__0 ) )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:74:1: ( ( rule__Target__Group__0 ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:75:1: ( rule__Target__Group__0 )
            {
             before(grammarAccess.getTargetAccess().getGroup()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:76:1: ( rule__Target__Group__0 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:76:2: rule__Target__Group__0
            {
            pushFollow(FOLLOW_rule__Target__Group__0_in_ruleTarget94);
            rule__Target__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTargetAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTarget"


    // $ANTLR start "entryRuleLocation"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:88:1: entryRuleLocation : ruleLocation EOF ;
    public final void entryRuleLocation() throws RecognitionException {
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:89:1: ( ruleLocation EOF )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:90:1: ruleLocation EOF
            {
             before(grammarAccess.getLocationRule()); 
            pushFollow(FOLLOW_ruleLocation_in_entryRuleLocation121);
            ruleLocation();

            state._fsp--;

             after(grammarAccess.getLocationRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocation128); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleLocation"


    // $ANTLR start "ruleLocation"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:97:1: ruleLocation : ( ( rule__Location__Group__0 ) ) ;
    public final void ruleLocation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:101:2: ( ( ( rule__Location__Group__0 ) ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:102:1: ( ( rule__Location__Group__0 ) )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:102:1: ( ( rule__Location__Group__0 ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:103:1: ( rule__Location__Group__0 )
            {
             before(grammarAccess.getLocationAccess().getGroup()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:104:1: ( rule__Location__Group__0 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:104:2: rule__Location__Group__0
            {
            pushFollow(FOLLOW_rule__Location__Group__0_in_ruleLocation154);
            rule__Location__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getLocationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLocation"


    // $ANTLR start "entryRuleUnit"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:116:1: entryRuleUnit : ruleUnit EOF ;
    public final void entryRuleUnit() throws RecognitionException {
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:117:1: ( ruleUnit EOF )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:118:1: ruleUnit EOF
            {
             before(grammarAccess.getUnitRule()); 
            pushFollow(FOLLOW_ruleUnit_in_entryRuleUnit181);
            ruleUnit();

            state._fsp--;

             after(grammarAccess.getUnitRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnit188); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleUnit"


    // $ANTLR start "ruleUnit"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:125:1: ruleUnit : ( ( rule__Unit__Group__0 ) ) ;
    public final void ruleUnit() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:129:2: ( ( ( rule__Unit__Group__0 ) ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:130:1: ( ( rule__Unit__Group__0 ) )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:130:1: ( ( rule__Unit__Group__0 ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:131:1: ( rule__Unit__Group__0 )
            {
             before(grammarAccess.getUnitAccess().getGroup()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:132:1: ( rule__Unit__Group__0 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:132:2: rule__Unit__Group__0
            {
            pushFollow(FOLLOW_rule__Unit__Group__0_in_ruleUnit214);
            rule__Unit__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getUnitAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUnit"


    // $ANTLR start "rule__Target__Group__0"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:146:1: rule__Target__Group__0 : rule__Target__Group__0__Impl rule__Target__Group__1 ;
    public final void rule__Target__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:150:1: ( rule__Target__Group__0__Impl rule__Target__Group__1 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:151:2: rule__Target__Group__0__Impl rule__Target__Group__1
            {
            pushFollow(FOLLOW_rule__Target__Group__0__Impl_in_rule__Target__Group__0248);
            rule__Target__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Target__Group__1_in_rule__Target__Group__0251);
            rule__Target__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__Group__0"


    // $ANTLR start "rule__Target__Group__0__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:158:1: rule__Target__Group__0__Impl : ( 'TargetDefinition' ) ;
    public final void rule__Target__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:162:1: ( ( 'TargetDefinition' ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:163:1: ( 'TargetDefinition' )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:163:1: ( 'TargetDefinition' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:164:1: 'TargetDefinition'
            {
             before(grammarAccess.getTargetAccess().getTargetDefinitionKeyword_0()); 
            match(input,12,FOLLOW_12_in_rule__Target__Group__0__Impl279); 
             after(grammarAccess.getTargetAccess().getTargetDefinitionKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__Group__0__Impl"


    // $ANTLR start "rule__Target__Group__1"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:177:1: rule__Target__Group__1 : rule__Target__Group__1__Impl rule__Target__Group__2 ;
    public final void rule__Target__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:181:1: ( rule__Target__Group__1__Impl rule__Target__Group__2 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:182:2: rule__Target__Group__1__Impl rule__Target__Group__2
            {
            pushFollow(FOLLOW_rule__Target__Group__1__Impl_in_rule__Target__Group__1310);
            rule__Target__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Target__Group__2_in_rule__Target__Group__1313);
            rule__Target__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__Group__1"


    // $ANTLR start "rule__Target__Group__1__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:189:1: rule__Target__Group__1__Impl : ( ( rule__Target__NameAssignment_1 ) ) ;
    public final void rule__Target__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:193:1: ( ( ( rule__Target__NameAssignment_1 ) ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:194:1: ( ( rule__Target__NameAssignment_1 ) )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:194:1: ( ( rule__Target__NameAssignment_1 ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:195:1: ( rule__Target__NameAssignment_1 )
            {
             before(grammarAccess.getTargetAccess().getNameAssignment_1()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:196:1: ( rule__Target__NameAssignment_1 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:196:2: rule__Target__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__Target__NameAssignment_1_in_rule__Target__Group__1__Impl340);
            rule__Target__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getTargetAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__Group__1__Impl"


    // $ANTLR start "rule__Target__Group__2"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:206:1: rule__Target__Group__2 : rule__Target__Group__2__Impl rule__Target__Group__3 ;
    public final void rule__Target__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:210:1: ( rule__Target__Group__2__Impl rule__Target__Group__3 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:211:2: rule__Target__Group__2__Impl rule__Target__Group__3
            {
            pushFollow(FOLLOW_rule__Target__Group__2__Impl_in_rule__Target__Group__2370);
            rule__Target__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Target__Group__3_in_rule__Target__Group__2373);
            rule__Target__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__Group__2"


    // $ANTLR start "rule__Target__Group__2__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:218:1: rule__Target__Group__2__Impl : ( ( rule__Target__Group_2__0 )? ) ;
    public final void rule__Target__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:222:1: ( ( ( rule__Target__Group_2__0 )? ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:223:1: ( ( rule__Target__Group_2__0 )? )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:223:1: ( ( rule__Target__Group_2__0 )? )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:224:1: ( rule__Target__Group_2__0 )?
            {
             before(grammarAccess.getTargetAccess().getGroup_2()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:225:1: ( rule__Target__Group_2__0 )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==13) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:225:2: rule__Target__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__Target__Group_2__0_in_rule__Target__Group__2__Impl400);
                    rule__Target__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTargetAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__Group__2__Impl"


    // $ANTLR start "rule__Target__Group__3"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:235:1: rule__Target__Group__3 : rule__Target__Group__3__Impl ;
    public final void rule__Target__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:239:1: ( rule__Target__Group__3__Impl )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:240:2: rule__Target__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Target__Group__3__Impl_in_rule__Target__Group__3431);
            rule__Target__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__Group__3"


    // $ANTLR start "rule__Target__Group__3__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:246:1: rule__Target__Group__3__Impl : ( ( rule__Target__LocationsAssignment_3 )* ) ;
    public final void rule__Target__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:250:1: ( ( ( rule__Target__LocationsAssignment_3 )* ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:251:1: ( ( rule__Target__LocationsAssignment_3 )* )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:251:1: ( ( rule__Target__LocationsAssignment_3 )* )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:252:1: ( rule__Target__LocationsAssignment_3 )*
            {
             before(grammarAccess.getTargetAccess().getLocationsAssignment_3()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:253:1: ( rule__Target__LocationsAssignment_3 )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_STRING||LA2_0==RULE_URL) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:253:2: rule__Target__LocationsAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__Target__LocationsAssignment_3_in_rule__Target__Group__3__Impl458);
            	    rule__Target__LocationsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

             after(grammarAccess.getTargetAccess().getLocationsAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__Group__3__Impl"


    // $ANTLR start "rule__Target__Group_2__0"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:271:1: rule__Target__Group_2__0 : rule__Target__Group_2__0__Impl rule__Target__Group_2__1 ;
    public final void rule__Target__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:275:1: ( rule__Target__Group_2__0__Impl rule__Target__Group_2__1 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:276:2: rule__Target__Group_2__0__Impl rule__Target__Group_2__1
            {
            pushFollow(FOLLOW_rule__Target__Group_2__0__Impl_in_rule__Target__Group_2__0497);
            rule__Target__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Target__Group_2__1_in_rule__Target__Group_2__0500);
            rule__Target__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__Group_2__0"


    // $ANTLR start "rule__Target__Group_2__0__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:283:1: rule__Target__Group_2__0__Impl : ( 'targetFile' ) ;
    public final void rule__Target__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:287:1: ( ( 'targetFile' ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:288:1: ( 'targetFile' )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:288:1: ( 'targetFile' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:289:1: 'targetFile'
            {
             before(grammarAccess.getTargetAccess().getTargetFileKeyword_2_0()); 
            match(input,13,FOLLOW_13_in_rule__Target__Group_2__0__Impl528); 
             after(grammarAccess.getTargetAccess().getTargetFileKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__Group_2__0__Impl"


    // $ANTLR start "rule__Target__Group_2__1"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:302:1: rule__Target__Group_2__1 : rule__Target__Group_2__1__Impl rule__Target__Group_2__2 ;
    public final void rule__Target__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:306:1: ( rule__Target__Group_2__1__Impl rule__Target__Group_2__2 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:307:2: rule__Target__Group_2__1__Impl rule__Target__Group_2__2
            {
            pushFollow(FOLLOW_rule__Target__Group_2__1__Impl_in_rule__Target__Group_2__1559);
            rule__Target__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Target__Group_2__2_in_rule__Target__Group_2__1562);
            rule__Target__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__Group_2__1"


    // $ANTLR start "rule__Target__Group_2__1__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:314:1: rule__Target__Group_2__1__Impl : ( '=' ) ;
    public final void rule__Target__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:318:1: ( ( '=' ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:319:1: ( '=' )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:319:1: ( '=' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:320:1: '='
            {
             before(grammarAccess.getTargetAccess().getEqualsSignKeyword_2_1()); 
            match(input,14,FOLLOW_14_in_rule__Target__Group_2__1__Impl590); 
             after(grammarAccess.getTargetAccess().getEqualsSignKeyword_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__Group_2__1__Impl"


    // $ANTLR start "rule__Target__Group_2__2"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:333:1: rule__Target__Group_2__2 : rule__Target__Group_2__2__Impl ;
    public final void rule__Target__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:337:1: ( rule__Target__Group_2__2__Impl )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:338:2: rule__Target__Group_2__2__Impl
            {
            pushFollow(FOLLOW_rule__Target__Group_2__2__Impl_in_rule__Target__Group_2__2621);
            rule__Target__Group_2__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__Group_2__2"


    // $ANTLR start "rule__Target__Group_2__2__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:344:1: rule__Target__Group_2__2__Impl : ( ( rule__Target__TargetFileNameAssignment_2_2 ) ) ;
    public final void rule__Target__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:348:1: ( ( ( rule__Target__TargetFileNameAssignment_2_2 ) ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:349:1: ( ( rule__Target__TargetFileNameAssignment_2_2 ) )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:349:1: ( ( rule__Target__TargetFileNameAssignment_2_2 ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:350:1: ( rule__Target__TargetFileNameAssignment_2_2 )
            {
             before(grammarAccess.getTargetAccess().getTargetFileNameAssignment_2_2()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:351:1: ( rule__Target__TargetFileNameAssignment_2_2 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:351:2: rule__Target__TargetFileNameAssignment_2_2
            {
            pushFollow(FOLLOW_rule__Target__TargetFileNameAssignment_2_2_in_rule__Target__Group_2__2__Impl648);
            rule__Target__TargetFileNameAssignment_2_2();

            state._fsp--;


            }

             after(grammarAccess.getTargetAccess().getTargetFileNameAssignment_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__Group_2__2__Impl"


    // $ANTLR start "rule__Location__Group__0"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:367:1: rule__Location__Group__0 : rule__Location__Group__0__Impl rule__Location__Group__1 ;
    public final void rule__Location__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:371:1: ( rule__Location__Group__0__Impl rule__Location__Group__1 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:372:2: rule__Location__Group__0__Impl rule__Location__Group__1
            {
            pushFollow(FOLLOW_rule__Location__Group__0__Impl_in_rule__Location__Group__0684);
            rule__Location__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Location__Group__1_in_rule__Location__Group__0687);
            rule__Location__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group__0"


    // $ANTLR start "rule__Location__Group__0__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:379:1: rule__Location__Group__0__Impl : ( ( rule__Location__NameAssignment_0 )? ) ;
    public final void rule__Location__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:383:1: ( ( ( rule__Location__NameAssignment_0 )? ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:384:1: ( ( rule__Location__NameAssignment_0 )? )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:384:1: ( ( rule__Location__NameAssignment_0 )? )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:385:1: ( rule__Location__NameAssignment_0 )?
            {
             before(grammarAccess.getLocationAccess().getNameAssignment_0()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:386:1: ( rule__Location__NameAssignment_0 )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_STRING) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:386:2: rule__Location__NameAssignment_0
                    {
                    pushFollow(FOLLOW_rule__Location__NameAssignment_0_in_rule__Location__Group__0__Impl714);
                    rule__Location__NameAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getLocationAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group__0__Impl"


    // $ANTLR start "rule__Location__Group__1"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:396:1: rule__Location__Group__1 : rule__Location__Group__1__Impl rule__Location__Group__2 ;
    public final void rule__Location__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:400:1: ( rule__Location__Group__1__Impl rule__Location__Group__2 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:401:2: rule__Location__Group__1__Impl rule__Location__Group__2
            {
            pushFollow(FOLLOW_rule__Location__Group__1__Impl_in_rule__Location__Group__1745);
            rule__Location__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Location__Group__2_in_rule__Location__Group__1748);
            rule__Location__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group__1"


    // $ANTLR start "rule__Location__Group__1__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:408:1: rule__Location__Group__1__Impl : ( ( rule__Location__RepositoryLocationAssignment_1 ) ) ;
    public final void rule__Location__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:412:1: ( ( ( rule__Location__RepositoryLocationAssignment_1 ) ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:413:1: ( ( rule__Location__RepositoryLocationAssignment_1 ) )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:413:1: ( ( rule__Location__RepositoryLocationAssignment_1 ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:414:1: ( rule__Location__RepositoryLocationAssignment_1 )
            {
             before(grammarAccess.getLocationAccess().getRepositoryLocationAssignment_1()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:415:1: ( rule__Location__RepositoryLocationAssignment_1 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:415:2: rule__Location__RepositoryLocationAssignment_1
            {
            pushFollow(FOLLOW_rule__Location__RepositoryLocationAssignment_1_in_rule__Location__Group__1__Impl775);
            rule__Location__RepositoryLocationAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getLocationAccess().getRepositoryLocationAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group__1__Impl"


    // $ANTLR start "rule__Location__Group__2"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:425:1: rule__Location__Group__2 : rule__Location__Group__2__Impl rule__Location__Group__3 ;
    public final void rule__Location__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:429:1: ( rule__Location__Group__2__Impl rule__Location__Group__3 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:430:2: rule__Location__Group__2__Impl rule__Location__Group__3
            {
            pushFollow(FOLLOW_rule__Location__Group__2__Impl_in_rule__Location__Group__2805);
            rule__Location__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Location__Group__3_in_rule__Location__Group__2808);
            rule__Location__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group__2"


    // $ANTLR start "rule__Location__Group__2__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:437:1: rule__Location__Group__2__Impl : ( '{' ) ;
    public final void rule__Location__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:441:1: ( ( '{' ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:442:1: ( '{' )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:442:1: ( '{' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:443:1: '{'
            {
             before(grammarAccess.getLocationAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,15,FOLLOW_15_in_rule__Location__Group__2__Impl836); 
             after(grammarAccess.getLocationAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group__2__Impl"


    // $ANTLR start "rule__Location__Group__3"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:456:1: rule__Location__Group__3 : rule__Location__Group__3__Impl rule__Location__Group__4 ;
    public final void rule__Location__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:460:1: ( rule__Location__Group__3__Impl rule__Location__Group__4 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:461:2: rule__Location__Group__3__Impl rule__Location__Group__4
            {
            pushFollow(FOLLOW_rule__Location__Group__3__Impl_in_rule__Location__Group__3867);
            rule__Location__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Location__Group__4_in_rule__Location__Group__3870);
            rule__Location__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group__3"


    // $ANTLR start "rule__Location__Group__3__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:468:1: rule__Location__Group__3__Impl : ( ( rule__Location__UnitAssignment_3 ) ) ;
    public final void rule__Location__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:472:1: ( ( ( rule__Location__UnitAssignment_3 ) ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:473:1: ( ( rule__Location__UnitAssignment_3 ) )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:473:1: ( ( rule__Location__UnitAssignment_3 ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:474:1: ( rule__Location__UnitAssignment_3 )
            {
             before(grammarAccess.getLocationAccess().getUnitAssignment_3()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:475:1: ( rule__Location__UnitAssignment_3 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:475:2: rule__Location__UnitAssignment_3
            {
            pushFollow(FOLLOW_rule__Location__UnitAssignment_3_in_rule__Location__Group__3__Impl897);
            rule__Location__UnitAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getLocationAccess().getUnitAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group__3__Impl"


    // $ANTLR start "rule__Location__Group__4"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:485:1: rule__Location__Group__4 : rule__Location__Group__4__Impl rule__Location__Group__5 ;
    public final void rule__Location__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:489:1: ( rule__Location__Group__4__Impl rule__Location__Group__5 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:490:2: rule__Location__Group__4__Impl rule__Location__Group__5
            {
            pushFollow(FOLLOW_rule__Location__Group__4__Impl_in_rule__Location__Group__4927);
            rule__Location__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Location__Group__5_in_rule__Location__Group__4930);
            rule__Location__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group__4"


    // $ANTLR start "rule__Location__Group__4__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:497:1: rule__Location__Group__4__Impl : ( ( rule__Location__Group_4__0 )* ) ;
    public final void rule__Location__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:501:1: ( ( ( rule__Location__Group_4__0 )* ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:502:1: ( ( rule__Location__Group_4__0 )* )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:502:1: ( ( rule__Location__Group_4__0 )* )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:503:1: ( rule__Location__Group_4__0 )*
            {
             before(grammarAccess.getLocationAccess().getGroup_4()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:504:1: ( rule__Location__Group_4__0 )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==17) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:504:2: rule__Location__Group_4__0
            	    {
            	    pushFollow(FOLLOW_rule__Location__Group_4__0_in_rule__Location__Group__4__Impl957);
            	    rule__Location__Group_4__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

             after(grammarAccess.getLocationAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group__4__Impl"


    // $ANTLR start "rule__Location__Group__5"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:514:1: rule__Location__Group__5 : rule__Location__Group__5__Impl ;
    public final void rule__Location__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:518:1: ( rule__Location__Group__5__Impl )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:519:2: rule__Location__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__Location__Group__5__Impl_in_rule__Location__Group__5988);
            rule__Location__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group__5"


    // $ANTLR start "rule__Location__Group__5__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:525:1: rule__Location__Group__5__Impl : ( '}' ) ;
    public final void rule__Location__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:529:1: ( ( '}' ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:530:1: ( '}' )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:530:1: ( '}' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:531:1: '}'
            {
             before(grammarAccess.getLocationAccess().getRightCurlyBracketKeyword_5()); 
            match(input,16,FOLLOW_16_in_rule__Location__Group__5__Impl1016); 
             after(grammarAccess.getLocationAccess().getRightCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group__5__Impl"


    // $ANTLR start "rule__Location__Group_4__0"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:556:1: rule__Location__Group_4__0 : rule__Location__Group_4__0__Impl rule__Location__Group_4__1 ;
    public final void rule__Location__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:560:1: ( rule__Location__Group_4__0__Impl rule__Location__Group_4__1 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:561:2: rule__Location__Group_4__0__Impl rule__Location__Group_4__1
            {
            pushFollow(FOLLOW_rule__Location__Group_4__0__Impl_in_rule__Location__Group_4__01059);
            rule__Location__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Location__Group_4__1_in_rule__Location__Group_4__01062);
            rule__Location__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group_4__0"


    // $ANTLR start "rule__Location__Group_4__0__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:568:1: rule__Location__Group_4__0__Impl : ( ',' ) ;
    public final void rule__Location__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:572:1: ( ( ',' ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:573:1: ( ',' )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:573:1: ( ',' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:574:1: ','
            {
             before(grammarAccess.getLocationAccess().getCommaKeyword_4_0()); 
            match(input,17,FOLLOW_17_in_rule__Location__Group_4__0__Impl1090); 
             after(grammarAccess.getLocationAccess().getCommaKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group_4__0__Impl"


    // $ANTLR start "rule__Location__Group_4__1"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:587:1: rule__Location__Group_4__1 : rule__Location__Group_4__1__Impl ;
    public final void rule__Location__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:591:1: ( rule__Location__Group_4__1__Impl )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:592:2: rule__Location__Group_4__1__Impl
            {
            pushFollow(FOLLOW_rule__Location__Group_4__1__Impl_in_rule__Location__Group_4__11121);
            rule__Location__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group_4__1"


    // $ANTLR start "rule__Location__Group_4__1__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:598:1: rule__Location__Group_4__1__Impl : ( ( rule__Location__UnitAssignment_4_1 ) ) ;
    public final void rule__Location__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:602:1: ( ( ( rule__Location__UnitAssignment_4_1 ) ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:603:1: ( ( rule__Location__UnitAssignment_4_1 ) )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:603:1: ( ( rule__Location__UnitAssignment_4_1 ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:604:1: ( rule__Location__UnitAssignment_4_1 )
            {
             before(grammarAccess.getLocationAccess().getUnitAssignment_4_1()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:605:1: ( rule__Location__UnitAssignment_4_1 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:605:2: rule__Location__UnitAssignment_4_1
            {
            pushFollow(FOLLOW_rule__Location__UnitAssignment_4_1_in_rule__Location__Group_4__1__Impl1148);
            rule__Location__UnitAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getLocationAccess().getUnitAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__Group_4__1__Impl"


    // $ANTLR start "rule__Unit__Group__0"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:619:1: rule__Unit__Group__0 : rule__Unit__Group__0__Impl rule__Unit__Group__1 ;
    public final void rule__Unit__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:623:1: ( rule__Unit__Group__0__Impl rule__Unit__Group__1 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:624:2: rule__Unit__Group__0__Impl rule__Unit__Group__1
            {
            pushFollow(FOLLOW_rule__Unit__Group__0__Impl_in_rule__Unit__Group__01182);
            rule__Unit__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Unit__Group__1_in_rule__Unit__Group__01185);
            rule__Unit__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unit__Group__0"


    // $ANTLR start "rule__Unit__Group__0__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:631:1: rule__Unit__Group__0__Impl : ( ( rule__Unit__CategoryIdAssignment_0 ) ) ;
    public final void rule__Unit__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:635:1: ( ( ( rule__Unit__CategoryIdAssignment_0 ) ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:636:1: ( ( rule__Unit__CategoryIdAssignment_0 ) )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:636:1: ( ( rule__Unit__CategoryIdAssignment_0 ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:637:1: ( rule__Unit__CategoryIdAssignment_0 )
            {
             before(grammarAccess.getUnitAccess().getCategoryIdAssignment_0()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:638:1: ( rule__Unit__CategoryIdAssignment_0 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:638:2: rule__Unit__CategoryIdAssignment_0
            {
            pushFollow(FOLLOW_rule__Unit__CategoryIdAssignment_0_in_rule__Unit__Group__0__Impl1212);
            rule__Unit__CategoryIdAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getUnitAccess().getCategoryIdAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unit__Group__0__Impl"


    // $ANTLR start "rule__Unit__Group__1"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:648:1: rule__Unit__Group__1 : rule__Unit__Group__1__Impl rule__Unit__Group__2 ;
    public final void rule__Unit__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:652:1: ( rule__Unit__Group__1__Impl rule__Unit__Group__2 )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:653:2: rule__Unit__Group__1__Impl rule__Unit__Group__2
            {
            pushFollow(FOLLOW_rule__Unit__Group__1__Impl_in_rule__Unit__Group__11242);
            rule__Unit__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Unit__Group__2_in_rule__Unit__Group__11245);
            rule__Unit__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unit__Group__1"


    // $ANTLR start "rule__Unit__Group__1__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:660:1: rule__Unit__Group__1__Impl : ( ( rule__Unit__VersionAssignment_1 )? ) ;
    public final void rule__Unit__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:664:1: ( ( ( rule__Unit__VersionAssignment_1 )? ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:665:1: ( ( rule__Unit__VersionAssignment_1 )? )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:665:1: ( ( rule__Unit__VersionAssignment_1 )? )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:666:1: ( rule__Unit__VersionAssignment_1 )?
            {
             before(grammarAccess.getUnitAccess().getVersionAssignment_1()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:667:1: ( rule__Unit__VersionAssignment_1 )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_VERSION) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:667:2: rule__Unit__VersionAssignment_1
                    {
                    pushFollow(FOLLOW_rule__Unit__VersionAssignment_1_in_rule__Unit__Group__1__Impl1272);
                    rule__Unit__VersionAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getUnitAccess().getVersionAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unit__Group__1__Impl"


    // $ANTLR start "rule__Unit__Group__2"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:677:1: rule__Unit__Group__2 : rule__Unit__Group__2__Impl ;
    public final void rule__Unit__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:681:1: ( rule__Unit__Group__2__Impl )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:682:2: rule__Unit__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__Unit__Group__2__Impl_in_rule__Unit__Group__21303);
            rule__Unit__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unit__Group__2"


    // $ANTLR start "rule__Unit__Group__2__Impl"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:688:1: rule__Unit__Group__2__Impl : ( ( rule__Unit__NoFeatureAssignment_2 )? ) ;
    public final void rule__Unit__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:692:1: ( ( ( rule__Unit__NoFeatureAssignment_2 )? ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:693:1: ( ( rule__Unit__NoFeatureAssignment_2 )? )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:693:1: ( ( rule__Unit__NoFeatureAssignment_2 )? )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:694:1: ( rule__Unit__NoFeatureAssignment_2 )?
            {
             before(grammarAccess.getUnitAccess().getNoFeatureAssignment_2()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:695:1: ( rule__Unit__NoFeatureAssignment_2 )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==18) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:695:2: rule__Unit__NoFeatureAssignment_2
                    {
                    pushFollow(FOLLOW_rule__Unit__NoFeatureAssignment_2_in_rule__Unit__Group__2__Impl1330);
                    rule__Unit__NoFeatureAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getUnitAccess().getNoFeatureAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unit__Group__2__Impl"


    // $ANTLR start "rule__Target__NameAssignment_1"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:712:1: rule__Target__NameAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Target__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:716:1: ( ( RULE_STRING ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:717:1: ( RULE_STRING )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:717:1: ( RULE_STRING )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:718:1: RULE_STRING
            {
             before(grammarAccess.getTargetAccess().getNameSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Target__NameAssignment_11372); 
             after(grammarAccess.getTargetAccess().getNameSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__NameAssignment_1"


    // $ANTLR start "rule__Target__TargetFileNameAssignment_2_2"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:727:1: rule__Target__TargetFileNameAssignment_2_2 : ( RULE_ID ) ;
    public final void rule__Target__TargetFileNameAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:731:1: ( ( RULE_ID ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:732:1: ( RULE_ID )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:732:1: ( RULE_ID )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:733:1: RULE_ID
            {
             before(grammarAccess.getTargetAccess().getTargetFileNameIDTerminalRuleCall_2_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Target__TargetFileNameAssignment_2_21403); 
             after(grammarAccess.getTargetAccess().getTargetFileNameIDTerminalRuleCall_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__TargetFileNameAssignment_2_2"


    // $ANTLR start "rule__Target__LocationsAssignment_3"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:742:1: rule__Target__LocationsAssignment_3 : ( ruleLocation ) ;
    public final void rule__Target__LocationsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:746:1: ( ( ruleLocation ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:747:1: ( ruleLocation )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:747:1: ( ruleLocation )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:748:1: ruleLocation
            {
             before(grammarAccess.getTargetAccess().getLocationsLocationParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleLocation_in_rule__Target__LocationsAssignment_31434);
            ruleLocation();

            state._fsp--;

             after(grammarAccess.getTargetAccess().getLocationsLocationParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Target__LocationsAssignment_3"


    // $ANTLR start "rule__Location__NameAssignment_0"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:757:1: rule__Location__NameAssignment_0 : ( RULE_STRING ) ;
    public final void rule__Location__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:761:1: ( ( RULE_STRING ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:762:1: ( RULE_STRING )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:762:1: ( RULE_STRING )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:763:1: RULE_STRING
            {
             before(grammarAccess.getLocationAccess().getNameSTRINGTerminalRuleCall_0_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Location__NameAssignment_01465); 
             after(grammarAccess.getLocationAccess().getNameSTRINGTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__NameAssignment_0"


    // $ANTLR start "rule__Location__RepositoryLocationAssignment_1"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:772:1: rule__Location__RepositoryLocationAssignment_1 : ( RULE_URL ) ;
    public final void rule__Location__RepositoryLocationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:776:1: ( ( RULE_URL ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:777:1: ( RULE_URL )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:777:1: ( RULE_URL )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:778:1: RULE_URL
            {
             before(grammarAccess.getLocationAccess().getRepositoryLocationURLTerminalRuleCall_1_0()); 
            match(input,RULE_URL,FOLLOW_RULE_URL_in_rule__Location__RepositoryLocationAssignment_11496); 
             after(grammarAccess.getLocationAccess().getRepositoryLocationURLTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__RepositoryLocationAssignment_1"


    // $ANTLR start "rule__Location__UnitAssignment_3"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:787:1: rule__Location__UnitAssignment_3 : ( ruleUnit ) ;
    public final void rule__Location__UnitAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:791:1: ( ( ruleUnit ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:792:1: ( ruleUnit )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:792:1: ( ruleUnit )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:793:1: ruleUnit
            {
             before(grammarAccess.getLocationAccess().getUnitUnitParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleUnit_in_rule__Location__UnitAssignment_31527);
            ruleUnit();

            state._fsp--;

             after(grammarAccess.getLocationAccess().getUnitUnitParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__UnitAssignment_3"


    // $ANTLR start "rule__Location__UnitAssignment_4_1"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:802:1: rule__Location__UnitAssignment_4_1 : ( ruleUnit ) ;
    public final void rule__Location__UnitAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:806:1: ( ( ruleUnit ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:807:1: ( ruleUnit )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:807:1: ( ruleUnit )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:808:1: ruleUnit
            {
             before(grammarAccess.getLocationAccess().getUnitUnitParserRuleCall_4_1_0()); 
            pushFollow(FOLLOW_ruleUnit_in_rule__Location__UnitAssignment_4_11558);
            ruleUnit();

            state._fsp--;

             after(grammarAccess.getLocationAccess().getUnitUnitParserRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Location__UnitAssignment_4_1"


    // $ANTLR start "rule__Unit__CategoryIdAssignment_0"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:817:1: rule__Unit__CategoryIdAssignment_0 : ( RULE_ID ) ;
    public final void rule__Unit__CategoryIdAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:821:1: ( ( RULE_ID ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:822:1: ( RULE_ID )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:822:1: ( RULE_ID )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:823:1: RULE_ID
            {
             before(grammarAccess.getUnitAccess().getCategoryIdIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Unit__CategoryIdAssignment_01589); 
             after(grammarAccess.getUnitAccess().getCategoryIdIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unit__CategoryIdAssignment_0"


    // $ANTLR start "rule__Unit__VersionAssignment_1"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:832:1: rule__Unit__VersionAssignment_1 : ( RULE_VERSION ) ;
    public final void rule__Unit__VersionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:836:1: ( ( RULE_VERSION ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:837:1: ( RULE_VERSION )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:837:1: ( RULE_VERSION )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:838:1: RULE_VERSION
            {
             before(grammarAccess.getUnitAccess().getVersionVERSIONTerminalRuleCall_1_0()); 
            match(input,RULE_VERSION,FOLLOW_RULE_VERSION_in_rule__Unit__VersionAssignment_11620); 
             after(grammarAccess.getUnitAccess().getVersionVERSIONTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unit__VersionAssignment_1"


    // $ANTLR start "rule__Unit__NoFeatureAssignment_2"
    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:847:1: rule__Unit__NoFeatureAssignment_2 : ( ( 'noFeature' ) ) ;
    public final void rule__Unit__NoFeatureAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:851:1: ( ( ( 'noFeature' ) ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:852:1: ( ( 'noFeature' ) )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:852:1: ( ( 'noFeature' ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:853:1: ( 'noFeature' )
            {
             before(grammarAccess.getUnitAccess().getNoFeatureNoFeatureKeyword_2_0()); 
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:854:1: ( 'noFeature' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:855:1: 'noFeature'
            {
             before(grammarAccess.getUnitAccess().getNoFeatureNoFeatureKeyword_2_0()); 
            match(input,18,FOLLOW_18_in_rule__Unit__NoFeatureAssignment_21656); 
             after(grammarAccess.getUnitAccess().getNoFeatureNoFeatureKeyword_2_0()); 

            }

             after(grammarAccess.getUnitAccess().getNoFeatureNoFeatureKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unit__NoFeatureAssignment_2"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleTarget_in_entryRuleTarget61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTarget68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Target__Group__0_in_ruleTarget94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocation_in_entryRuleLocation121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocation128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Location__Group__0_in_ruleLocation154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnit_in_entryRuleUnit181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnit188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Unit__Group__0_in_ruleUnit214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Target__Group__0__Impl_in_rule__Target__Group__0248 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Target__Group__1_in_rule__Target__Group__0251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Target__Group__0__Impl279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Target__Group__1__Impl_in_rule__Target__Group__1310 = new BitSet(new long[]{0x0000000000002050L});
    public static final BitSet FOLLOW_rule__Target__Group__2_in_rule__Target__Group__1313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Target__NameAssignment_1_in_rule__Target__Group__1__Impl340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Target__Group__2__Impl_in_rule__Target__Group__2370 = new BitSet(new long[]{0x0000000000002050L});
    public static final BitSet FOLLOW_rule__Target__Group__3_in_rule__Target__Group__2373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Target__Group_2__0_in_rule__Target__Group__2__Impl400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Target__Group__3__Impl_in_rule__Target__Group__3431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Target__LocationsAssignment_3_in_rule__Target__Group__3__Impl458 = new BitSet(new long[]{0x0000000000000052L});
    public static final BitSet FOLLOW_rule__Target__Group_2__0__Impl_in_rule__Target__Group_2__0497 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_rule__Target__Group_2__1_in_rule__Target__Group_2__0500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Target__Group_2__0__Impl528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Target__Group_2__1__Impl_in_rule__Target__Group_2__1559 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Target__Group_2__2_in_rule__Target__Group_2__1562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__Target__Group_2__1__Impl590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Target__Group_2__2__Impl_in_rule__Target__Group_2__2621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Target__TargetFileNameAssignment_2_2_in_rule__Target__Group_2__2__Impl648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Location__Group__0__Impl_in_rule__Location__Group__0684 = new BitSet(new long[]{0x0000000000000050L});
    public static final BitSet FOLLOW_rule__Location__Group__1_in_rule__Location__Group__0687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Location__NameAssignment_0_in_rule__Location__Group__0__Impl714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Location__Group__1__Impl_in_rule__Location__Group__1745 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__Location__Group__2_in_rule__Location__Group__1748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Location__RepositoryLocationAssignment_1_in_rule__Location__Group__1__Impl775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Location__Group__2__Impl_in_rule__Location__Group__2805 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Location__Group__3_in_rule__Location__Group__2808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Location__Group__2__Impl836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Location__Group__3__Impl_in_rule__Location__Group__3867 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_rule__Location__Group__4_in_rule__Location__Group__3870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Location__UnitAssignment_3_in_rule__Location__Group__3__Impl897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Location__Group__4__Impl_in_rule__Location__Group__4927 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_rule__Location__Group__5_in_rule__Location__Group__4930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Location__Group_4__0_in_rule__Location__Group__4__Impl957 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_rule__Location__Group__5__Impl_in_rule__Location__Group__5988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__Location__Group__5__Impl1016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Location__Group_4__0__Impl_in_rule__Location__Group_4__01059 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Location__Group_4__1_in_rule__Location__Group_4__01062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__Location__Group_4__0__Impl1090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Location__Group_4__1__Impl_in_rule__Location__Group_4__11121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Location__UnitAssignment_4_1_in_rule__Location__Group_4__1__Impl1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Unit__Group__0__Impl_in_rule__Unit__Group__01182 = new BitSet(new long[]{0x0000000000040080L});
    public static final BitSet FOLLOW_rule__Unit__Group__1_in_rule__Unit__Group__01185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Unit__CategoryIdAssignment_0_in_rule__Unit__Group__0__Impl1212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Unit__Group__1__Impl_in_rule__Unit__Group__11242 = new BitSet(new long[]{0x0000000000040080L});
    public static final BitSet FOLLOW_rule__Unit__Group__2_in_rule__Unit__Group__11245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Unit__VersionAssignment_1_in_rule__Unit__Group__1__Impl1272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Unit__Group__2__Impl_in_rule__Unit__Group__21303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Unit__NoFeatureAssignment_2_in_rule__Unit__Group__2__Impl1330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Target__NameAssignment_11372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Target__TargetFileNameAssignment_2_21403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocation_in_rule__Target__LocationsAssignment_31434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Location__NameAssignment_01465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_URL_in_rule__Location__RepositoryLocationAssignment_11496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnit_in_rule__Location__UnitAssignment_31527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnit_in_rule__Location__UnitAssignment_4_11558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Unit__CategoryIdAssignment_01589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_VERSION_in_rule__Unit__VersionAssignment_11620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__Unit__NoFeatureAssignment_21656 = new BitSet(new long[]{0x0000000000000002L});

}