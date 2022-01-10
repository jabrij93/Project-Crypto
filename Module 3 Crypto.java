public class module3_crypto {
    public static void main(String[] args) {
        String ns = normalizeText("This is some \"really\" great. (Text)!?");
        System.out.println("Normalized text: "+ns);
        String obf = obify(ns);
        System.out.println("Obified Text: "+obf);
        String uobf = unobify(obf);
        System.out.println("UnObified Text: "+uobf);
        String cr = caesarify("ILIKEAPPLES",1);
        System.out.println("Encryption of \"ILIKEAPPLES\": "+cr);
        String gr = groupify("ILIKEAPPLES",2);
        System.out.println("Groupified Text: "+gr);
        String ugr = ungroupify(gr);
        System.out.println("UnGroupified Text: "+ugr);
        System.out.println("\n-----------Encryption and Decryption for \"WHOWILLWINTHEELECTION\"----------\n");
        String cyphertext = encryptString("WHOWILLWINTHEELECTION", 1, 4);
        System.out.println("Encrypted Text: "+cyphertext);
        String plaintext = decryptString(cyphertext, -1);
        System.out.println("Decrypted Text: "+plaintext);;
    }

    //part 1 - normalizing text
//    public static String normalizeText(String str_text) {
//        String text = "This is some \"really\" great. (Text)!?";
//        System.out.println(text);
//        String noSpaceText = text.replaceAll("[^\\p{L}\\p{N}]","").toUpperCase();
//        return noSpaceText;
//    }

    public static String normalizeText(String str_text) {
        String text = "WHOWILLWINTHEELECTION";
        System.out.println(text);
        String noSpaceText = text.replaceAll("[^\\p{L}\\p{N}]","").toUpperCase();
        return noSpaceText;
    }

    //part 2 - caesar cipher
    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }

    public static String caesarify(String first_string, int shift_value) {
        String cipher = "";
        String alphabet = shiftAlphabet(0);
        String rotatedAlphabet = shiftAlphabet(shift_value);
        for (int i = 0; i <first_string.length(); i++) {
            cipher = cipher + rotatedAlphabet.charAt(alphabet.indexOf(first_string.charAt(i)));
        }
        return cipher;
    }

    // part 3 - codegroups
    public static String groupify(String str_text, int nmbr_of_letters) {
        int pad = str_text.length()%nmbr_of_letters;

        //padding x's to string
        while(pad>0){
            str_text = str_text + 'x';
            pad--;
        }
        //adding space
        for(int i=nmbr_of_letters;i<str_text.length();i=i+nmbr_of_letters){
            str_text = str_text.substring(0,i)+" "+str_text.substring(i,str_text.length());
            i++;
        }
        return str_text;
    }

    //part 4 - putting it all together

    public static String obify(String str_text) {
        for(int i=0;i<str_text.length();i++){
            //adding OB infront of vowels
            if((str_text.charAt(i) == 'A') || (str_text.charAt(i) == 'E') || (str_text.charAt(i) == 'I') ||(str_text.charAt(i) == 'O') ||(str_text.charAt(i) == 'U')||(str_text.charAt(i) == 'Y')) {
                str_text = str_text.substring(0, i) + "OB" + str_text.substring(i, str_text.length());
                i=i+2;
            }
        }
        return str_text;
    }

    public static String unobify(String str_text){
        int index;
        index = str_text.indexOf("OBA");
        while(index>=0){
            str_text = str_text.substring(0,index)+"A"+ str_text.substring(index+3, str_text.length());
            index = str_text.indexOf("OBA");
        }
        index = str_text.indexOf("OBE");
        while(index>=0){
            str_text = str_text.substring(0,index)+"E"+ str_text.substring(index+3, str_text.length());
            index = str_text.indexOf("OBE");
        }
        index = str_text.indexOf("OBI");
        while(index>=0){
            str_text = str_text.substring(0,index)+"I"+ str_text.substring(index+3, str_text.length());
            index = str_text.indexOf("OBI");
        }
        index = str_text.indexOf("OBO");
        while(index>=0){
            str_text = str_text.substring(0,index)+"O"+ str_text.substring(index+3, str_text.length());
            index = str_text.indexOf("OBO");
        }
        index = str_text.indexOf("OBU");
        while(index>=0){
            str_text = str_text.substring(0,index)+"U"+ str_text.substring(index+3, str_text.length());
            index = str_text.indexOf("OBU");
        }
        index = str_text.indexOf("OBY");
        while(index>=0){
            str_text = str_text.substring(0,index)+"Y"+ str_text.substring(index+3, str_text.length());
            index = str_text.indexOf("OBY");
        }
        return str_text;
    }

    public static String encryptString(String str_text, int shift_value, int nmbr_of_lttrs){
        str_text = normalizeText(str_text);
        str_text = obify(str_text);
        str_text = caesarify(str_text, shift_value);
        str_text = groupify(str_text, nmbr_of_lttrs);
        return str_text;
    }

    //part 5 - hacker problem
    public static String ungroupify(String str_text){
        str_text = str_text.replaceAll("\\s+","");
        str_text = str_text.replaceAll("x","");
        return str_text;
    }

    public static String decryptString(String str_text, int shift_value){
        str_text = ungroupify(str_text);
        str_text = caesarify(str_text,shift_value); //decryption of caesar cipher is just rotating string reverse
        str_text = unobify(str_text);
        return str_text;
    }
}

//"This is some \"really\" great. (Text)!?" // initialText

//THIS ISSO MERE ALLY GREA TTEX Tx // normalizeText

//THOBISOBISSOBOMOBEROBEOBALLOBYGROBEOBATTOBEXTx // obify

//UIPC JTPC JTTP CPNP CFSP CFPC BMMP CZHS PCFP CBUU PCFY Ux // caesarify and groupify

//VJKU KUUQ OGTG CNNA ITGC VVGZ Vx // forgot

//str_text = unobify(str_text);
