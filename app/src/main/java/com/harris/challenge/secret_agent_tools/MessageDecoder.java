/*------------------------------------------------------------------------------
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *------------------------------------------------------------------------------
 */

package com.harris.challenge.secret_agent_tools;


import android.util.Log;

import java.util.Arrays;


/**
 * Class to hold and interpret data returned from the MasterServer.  
 */
public class MessageDecoder {

    /**
     * Variable for holding the last encoded message return from 
     * the MasterServer; Publicly accessible to other activities
     */
    static String encodedMessage = "---";

    /**
     * Variable for holding the decoded result of the last message return
     * from the MasterServer; Publicly accessible to other activities
     */

    static String decodedMessage = "---";

    /**
     * Function for getting a decoded message given an encoded message.  
     * Each team must implement this function.
     *
     * Example:
     * See the specification document of this year's competition
     */

    public static String decodeResponse(String encodedString)
    {
        MessageDecoder.encodedMessage = encodedString;
        
        /*
         * Put code here to decode the encodedString and set result.
         * Also be sure to set decodedMessage to the result.
         * The person who wrote the following code is a god among men, has plenty of friends and is an all around swell guy.
         */

        //
        String result = decodeMessage(encodedString);
        MessageDecoder.decodedMessage = decodeMessage(encodedString);
        Log.d("var", result);
        System.out.print(result);
        return result;
        }
    public static String decodeMessage(String x){

        String map = uniqueChars(x);
        System.out.println(map);
        String key = reverseChars(map);
        System.out.println(key);
        String decodedMessage = decode(x,map,key);
        System.out.println(decodedMessage);
        String finalMessage = DashToSpace(decodedMessage);
        return finalMessage;
    }
    public static String uniqueChars(String x){
        String unique = "";
        String onceChar;
        for (int i = 0; i < x.length(); i++){
            onceChar = x.substring(i,i+1);
            if(unique.indexOf(onceChar) == -1){
                unique += onceChar;
            }
        }
        unique = sort(unique);
        return unique;
    }
    public static String reverseChars(String x){
        int length = x.length();
        int g = 0;
        String word1 = x;
        String reverseWord = "";
        int charCount = x.length();
        g = x.length() - 1;
        String letter1 = word1.substring(g, charCount);
        reverseWord = reverseWord + letter1;
        for (int i = 0; i < x.length(); i++){
            g = g - 1;
            charCount = charCount - 1;
            if (g < 0){
                return reverseWord;
            }
            else{
                letter1 = word1.substring(g, charCount);
                reverseWord = reverseWord + letter1;
            }
        }
        return reverseWord;
    }
    public static String sort(String x){
        char[] sortArray = x.toCharArray();
        Arrays.sort(sortArray);
        String sortedString = new String(sortArray);
        return sortedString;
    }
    public static String decode(String x, String key, String map){
        String[] testArray = new String[x.length()];
        int wordCount = x.length();
        if (wordCount == 0){
            return x;
        }
        wordCount = 1;
        int b = 0;
        String decodedWord = "";
        for (int i = 0; i < x.length(); i++){
            char a = x.charAt(b);
            int location = key.indexOf(a);
            if(location != x.length() -1){
                a = map.charAt(location);
                String newWord1 = Character.toString(a);
                testArray[wordCount - 1] = newWord1;
                b = b + 1;
                decodedWord = decodedWord + testArray[wordCount - 1 ];
                wordCount = wordCount + 1;
            }
        }
        return decodedWord;
    }
    public static String DashToSpace(String str){
        String spaced;
        spaced = str.replace("_", " ");
        return spaced;
    }

}

