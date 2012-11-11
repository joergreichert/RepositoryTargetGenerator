package de.abg.jreichert.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalTargetDefinitionLexer extends Lexer {
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

    public InternalTargetDefinitionLexer() {;} 
    public InternalTargetDefinitionLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalTargetDefinitionLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g"; }

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:11:7: ( 'TargetDefinition' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:11:9: 'TargetDefinition'
            {
            match("TargetDefinition"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:12:7: ( 'targetFile' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:12:9: 'targetFile'
            {
            match("targetFile"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:13:7: ( '=' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:13:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:14:7: ( '{' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:14:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:15:7: ( '}' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:15:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:16:7: ( ',' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:16:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:17:7: ( 'noFeature' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:17:9: 'noFeature'
            {
            match("noFeature"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "RULE_URL"
    public final void mRULE_URL() throws RecognitionException {
        try {
            int _type = RULE_URL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:871:10: ( ( 'http' | 'https' ) '://' ( 'a' .. 'z' | 'A' .. 'Z' | '?' | '&' | '%' | '$' | '/' | '.' | '_' | '-' | '0' .. '9' )* )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:871:12: ( 'http' | 'https' ) '://' ( 'a' .. 'z' | 'A' .. 'Z' | '?' | '&' | '%' | '$' | '/' | '.' | '_' | '-' | '0' .. '9' )*
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:871:12: ( 'http' | 'https' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='h') ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1=='t') ) {
                    int LA1_2 = input.LA(3);

                    if ( (LA1_2=='t') ) {
                        int LA1_3 = input.LA(4);

                        if ( (LA1_3=='p') ) {
                            int LA1_4 = input.LA(5);

                            if ( (LA1_4=='s') ) {
                                alt1=2;
                            }
                            else if ( (LA1_4==':') ) {
                                alt1=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 1, 4, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 1, 3, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 1, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:871:13: 'http'
                    {
                    match("http"); 


                    }
                    break;
                case 2 :
                    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:871:20: 'https'
                    {
                    match("https"); 


                    }
                    break;

            }

            match("://"); 

            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:871:35: ( 'a' .. 'z' | 'A' .. 'Z' | '?' | '&' | '%' | '$' | '/' | '.' | '_' | '-' | '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='$' && LA2_0<='&')||(LA2_0>='-' && LA2_0<='9')||LA2_0=='?'||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:
            	    {
            	    if ( (input.LA(1)>='$' && input.LA(1)<='&')||(input.LA(1)>='-' && input.LA(1)<='9')||input.LA(1)=='?'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_URL"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:873:9: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )* )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:873:11: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='-' && input.LA(1)<='.')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:873:43: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '.' | '-' | '0' .. '9' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='-' && LA3_0<='.')||(LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:
            	    {
            	    if ( (input.LA(1)>='-' && input.LA(1)<='.')||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_VERSION"
    public final void mRULE_VERSION() throws RecognitionException {
        try {
            int _type = RULE_VERSION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:875:14: ( ( '0' .. '9' )* ( '.' ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '-' | '0' .. '9' )* )* )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:875:16: ( '0' .. '9' )* ( '.' ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '-' | '0' .. '9' )* )*
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:875:16: ( '0' .. '9' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:875:17: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:875:28: ( '.' ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '-' | '0' .. '9' )* )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='.') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:875:29: '.' ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '-' | '0' .. '9' )*
            	    {
            	    match('.'); 
            	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:875:33: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '-' | '0' .. '9' )*
            	    loop5:
            	    do {
            	        int alt5=2;
            	        int LA5_0 = input.LA(1);

            	        if ( (LA5_0=='-'||(LA5_0>='0' && LA5_0<='9')||(LA5_0>='A' && LA5_0<='Z')||LA5_0=='_'||(LA5_0>='a' && LA5_0<='z')) ) {
            	            alt5=1;
            	        }


            	        switch (alt5) {
            	    	case 1 :
            	    	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:
            	    	    {
            	    	    if ( input.LA(1)=='-'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	    	        input.consume();

            	    	    }
            	    	    else {
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        recover(mse);
            	    	        throw mse;}


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop5;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_VERSION"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:877:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:877:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:877:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='\"') ) {
                alt9=1;
            }
            else if ( (LA9_0=='\'') ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:877:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:877:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop7:
                    do {
                        int alt7=3;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0=='\\') ) {
                            alt7=1;
                        }
                        else if ( ((LA7_0>='\u0000' && LA7_0<='!')||(LA7_0>='#' && LA7_0<='[')||(LA7_0>=']' && LA7_0<='\uFFFF')) ) {
                            alt7=2;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:877:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:877:66: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:877:86: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:877:91: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop8:
                    do {
                        int alt8=3;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0=='\\') ) {
                            alt8=1;
                        }
                        else if ( ((LA8_0>='\u0000' && LA8_0<='&')||(LA8_0>='(' && LA8_0<='[')||(LA8_0>=']' && LA8_0<='\uFFFF')) ) {
                            alt8=2;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:877:92: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:877:137: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:879:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:879:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:879:24: ( options {greedy=false; } : . )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0=='*') ) {
                    int LA10_1 = input.LA(2);

                    if ( (LA10_1=='/') ) {
                        alt10=2;
                    }
                    else if ( ((LA10_1>='\u0000' && LA10_1<='.')||(LA10_1>='0' && LA10_1<='\uFFFF')) ) {
                        alt10=1;
                    }


                }
                else if ( ((LA10_0>='\u0000' && LA10_0<=')')||(LA10_0>='+' && LA10_0<='\uFFFF')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:879:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:881:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:881:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:881:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='\u0000' && LA11_0<='\t')||(LA11_0>='\u000B' && LA11_0<='\f')||(LA11_0>='\u000E' && LA11_0<='\uFFFF')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:881:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:881:40: ( ( '\\r' )? '\\n' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='\n'||LA13_0=='\r') ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:881:41: ( '\\r' )? '\\n'
                    {
                    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:881:41: ( '\\r' )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0=='\r') ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:881:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:883:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:883:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:883:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>='\t' && LA14_0<='\n')||LA14_0=='\r'||LA14_0==' ') ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:885:16: ( . )
            // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:885:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:8: ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | RULE_URL | RULE_ID | RULE_VERSION | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt15=15;
        alt15 = dfa15.predict(input);
        switch (alt15) {
            case 1 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:10: T__12
                {
                mT__12(); 

                }
                break;
            case 2 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:16: T__13
                {
                mT__13(); 

                }
                break;
            case 3 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:22: T__14
                {
                mT__14(); 

                }
                break;
            case 4 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:28: T__15
                {
                mT__15(); 

                }
                break;
            case 5 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:34: T__16
                {
                mT__16(); 

                }
                break;
            case 6 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:40: T__17
                {
                mT__17(); 

                }
                break;
            case 7 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:46: T__18
                {
                mT__18(); 

                }
                break;
            case 8 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:52: RULE_URL
                {
                mRULE_URL(); 

                }
                break;
            case 9 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:61: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 10 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:69: RULE_VERSION
                {
                mRULE_VERSION(); 

                }
                break;
            case 11 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:82: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 12 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:94: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 13 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:110: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 14 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:126: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 15 :
                // ../de.abg.jreichert.repositorytarget.dsl.ui/src-gen/de/abg/jreichert/ui/contentassist/antlr/internal/InternalTargetDefinition.g:1:134: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA15 dfa15 = new DFA15(this);
    static final String DFA15_eotS =
        "\1\14\2\23\4\uffff\3\23\3\uffff\3\21\2\uffff\1\23\1\uffff\1\23"+
        "\4\uffff\4\23\4\uffff\14\23\1\uffff\13\23\1\74\1\23\1\76\1\uffff"+
        "\1\23\1\uffff\4\23\1\104\1\uffff";
    static final String DFA15_eofS =
        "\105\uffff";
    static final String DFA15_minS =
        "\1\0\2\141\4\uffff\1\157\1\164\1\55\3\uffff\2\0\1\52\2\uffff\1"+
        "\162\1\uffff\1\162\4\uffff\1\106\1\164\2\55\4\uffff\2\147\1\145"+
        "\1\160\2\145\1\141\1\72\3\164\1\72\1\uffff\1\104\1\106\1\165\1\145"+
        "\1\151\1\162\1\146\1\154\1\145\1\151\1\145\1\55\1\156\1\55\1\uffff"+
        "\1\151\1\uffff\1\164\1\151\1\157\1\156\1\55\1\uffff";
    static final String DFA15_maxS =
        "\1\uffff\2\141\4\uffff\1\157\1\164\1\172\3\uffff\2\uffff\1\57\2"+
        "\uffff\1\162\1\uffff\1\162\4\uffff\1\106\1\164\2\172\4\uffff\2\147"+
        "\1\145\1\160\2\145\1\141\1\163\3\164\1\72\1\uffff\1\104\1\106\1"+
        "\165\1\145\1\151\1\162\1\146\1\154\1\145\1\151\1\145\1\172\1\156"+
        "\1\172\1\uffff\1\151\1\uffff\1\164\1\151\1\157\1\156\1\172\1\uffff";
    static final String DFA15_acceptS =
        "\3\uffff\1\3\1\4\1\5\1\6\3\uffff\1\12\1\11\1\12\3\uffff\1\16\1"+
        "\17\1\uffff\1\11\1\uffff\1\3\1\4\1\5\1\6\4\uffff\1\13\1\14\1\15"+
        "\1\16\14\uffff\1\10\16\uffff\1\7\1\uffff\1\2\5\uffff\1\1";
    static final String DFA15_specialS =
        "\1\1\14\uffff\1\2\1\0\66\uffff}>";
    static final String[] DFA15_transitionS = {
            "\11\21\2\20\2\21\1\20\22\21\1\20\1\21\1\15\4\21\1\16\4\21\1"+
            "\6\1\13\1\11\1\17\12\12\3\21\1\3\3\21\23\13\1\1\6\13\4\21\1"+
            "\13\1\21\7\13\1\10\5\13\1\7\5\13\1\2\6\13\1\4\1\21\1\5\uff82"+
            "\21",
            "\1\22",
            "\1\24",
            "",
            "",
            "",
            "",
            "\1\31",
            "\1\32",
            "\1\33\1\34\1\uffff\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff"+
            "\32\33",
            "",
            "",
            "",
            "\0\35",
            "\0\35",
            "\1\36\4\uffff\1\37",
            "",
            "",
            "\1\41",
            "",
            "\1\42",
            "",
            "",
            "",
            "",
            "\1\43",
            "\1\44",
            "\1\33\1\34\1\uffff\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff"+
            "\32\33",
            "\1\33\1\34\1\uffff\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff"+
            "\32\33",
            "",
            "",
            "",
            "",
            "\1\45",
            "\1\46",
            "\1\47",
            "\1\50",
            "\1\51",
            "\1\52",
            "\1\53",
            "\1\55\70\uffff\1\54",
            "\1\56",
            "\1\57",
            "\1\60",
            "\1\55",
            "",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\2\23\1\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32"+
            "\23",
            "\1\75",
            "\2\23\1\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32"+
            "\23",
            "",
            "\1\77",
            "",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\2\23\1\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32"+
            "\23",
            ""
    };

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | RULE_URL | RULE_ID | RULE_VERSION | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA15_14 = input.LA(1);

                        s = -1;
                        if ( ((LA15_14>='\u0000' && LA15_14<='\uFFFF')) ) {s = 29;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA15_0 = input.LA(1);

                        s = -1;
                        if ( (LA15_0=='T') ) {s = 1;}

                        else if ( (LA15_0=='t') ) {s = 2;}

                        else if ( (LA15_0=='=') ) {s = 3;}

                        else if ( (LA15_0=='{') ) {s = 4;}

                        else if ( (LA15_0=='}') ) {s = 5;}

                        else if ( (LA15_0==',') ) {s = 6;}

                        else if ( (LA15_0=='n') ) {s = 7;}

                        else if ( (LA15_0=='h') ) {s = 8;}

                        else if ( (LA15_0=='.') ) {s = 9;}

                        else if ( ((LA15_0>='0' && LA15_0<='9')) ) {s = 10;}

                        else if ( (LA15_0=='-'||(LA15_0>='A' && LA15_0<='S')||(LA15_0>='U' && LA15_0<='Z')||LA15_0=='_'||(LA15_0>='a' && LA15_0<='g')||(LA15_0>='i' && LA15_0<='m')||(LA15_0>='o' && LA15_0<='s')||(LA15_0>='u' && LA15_0<='z')) ) {s = 11;}

                        else if ( (LA15_0=='\"') ) {s = 13;}

                        else if ( (LA15_0=='\'') ) {s = 14;}

                        else if ( (LA15_0=='/') ) {s = 15;}

                        else if ( ((LA15_0>='\t' && LA15_0<='\n')||LA15_0=='\r'||LA15_0==' ') ) {s = 16;}

                        else if ( ((LA15_0>='\u0000' && LA15_0<='\b')||(LA15_0>='\u000B' && LA15_0<='\f')||(LA15_0>='\u000E' && LA15_0<='\u001F')||LA15_0=='!'||(LA15_0>='#' && LA15_0<='&')||(LA15_0>='(' && LA15_0<='+')||(LA15_0>=':' && LA15_0<='<')||(LA15_0>='>' && LA15_0<='@')||(LA15_0>='[' && LA15_0<='^')||LA15_0=='`'||LA15_0=='|'||(LA15_0>='~' && LA15_0<='\uFFFF')) ) {s = 17;}

                        else s = 12;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA15_13 = input.LA(1);

                        s = -1;
                        if ( ((LA15_13>='\u0000' && LA15_13<='\uFFFF')) ) {s = 29;}

                        else s = 17;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 15, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}