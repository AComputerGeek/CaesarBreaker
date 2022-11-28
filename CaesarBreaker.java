import edu.duke.*;

/**
* 
* @author: Amir Armion 
* 
* @version: V.01
* 
*/
public class CaesarBreaker 
{

    public String encrypt(String input, int key)
    {
        String alphabet        = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        StringBuilder output   = new StringBuilder("");

        for(int i = 0; i < input.length(); i++)
        {
            char currentChar = input.charAt(i);

            if(Character.isUpperCase(currentChar)) // Character is uppercase
            {
                int positionOfCurrentChar = alphabet.indexOf(currentChar);

                if(positionOfCurrentChar != -1)
                {
                    char cryptedChar = shiftedAlphabet.charAt(positionOfCurrentChar);
                    output.append(cryptedChar);
                }
                else
                {
                    output.append(currentChar);
                }
            }
            else if(Character.isLowerCase(currentChar)) // Character is lowercase
            {
                int positionOfCurrentChar = alphabet.indexOf(Character.toUpperCase(currentChar));

                if(positionOfCurrentChar != -1)
                {
                    char cryptedChar = Character.toLowerCase(shiftedAlphabet.charAt(positionOfCurrentChar));
                    output.append(cryptedChar);
                }
                else
                {
                    output.append(currentChar);
                }
            }
            else // Character is not letter (It's punctuation or digit)
            {
                output.append(currentChar);
            }
        }

        return output.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2)
    {
        String alphabet         = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);

        StringBuilder output    = new StringBuilder("");

        for(int i = 0; i < input.length(); i++)
        {
            char currentChar = input.charAt(i);

            if(i % 2 == 0) // Even index
            {
                if(Character.isUpperCase(currentChar))
                {
                    int positionOfCurrentChar = alphabet.indexOf(currentChar);

                    if(positionOfCurrentChar != -1)
                    {
                        char cryptedChar = shiftedAlphabet1.charAt(positionOfCurrentChar);
                        output.append(cryptedChar);
                    }
                    else
                    {
                        output.append(currentChar);
                    }
                }
                else if(Character.isLowerCase(currentChar))
                {
                    int positionOfCurrentChar = alphabet.indexOf(Character.toUpperCase(currentChar));

                    if(positionOfCurrentChar != -1)
                    {
                        char cryptedChar = Character.toLowerCase(shiftedAlphabet1.charAt(positionOfCurrentChar));
                        output.append(cryptedChar);
                    }
                    else
                    {
                        output.append(currentChar);
                    }
                }
                else
                {
                    output.append(currentChar);
                }
            }
            else // Odd index
            {
                if(Character.isUpperCase(currentChar))
                {
                    int positionOfCurrentChar = alphabet.indexOf(currentChar);

                    if(positionOfCurrentChar != -1)
                    {
                        char cryptedChar = shiftedAlphabet2.charAt(positionOfCurrentChar);
                        output.append(cryptedChar);
                    }
                    else
                    {
                        output.append(currentChar);
                    }
                }
                else if(Character.isLowerCase(currentChar))
                {
                    int positionOfCurrentChar = alphabet.indexOf(Character.toUpperCase(currentChar));

                    if(positionOfCurrentChar != -1)
                    {
                        char cryptedChar = Character.toLowerCase(shiftedAlphabet2.charAt(positionOfCurrentChar));
                        output.append(cryptedChar);
                    }
                    else
                    {
                        output.append(currentChar);
                    }
                }
                else
                {
                    output.append(currentChar);
                }
            }
        }

        return output.toString();
    }

    public int[] countLetters(String encrypted)
    {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[]  counts   = new int[26];

        for(int i = 0; i < encrypted.length(); i++)
        {
            char ch              = Character.toUpperCase(encrypted.charAt(i));
            int  indexOfThisChar = alphabet.indexOf(ch);

            if(indexOfThisChar != -1)
            {
                counts[indexOfThisChar]++;
            }
        }

        System.out.println("\n------ Number of occurrence of each letter in encrypted message: -------\n");

        for(int i = 0; i < counts.length; i++)
        {
            System.out.println("Cell " + i + "(" + alphabet.charAt(i) + ") is: " + counts[i]);
        }

        System.out.println();

        return counts;
    }

    public int indexOfMax(int[] counts)
    {
        int maxIndex = 0;

        for(int i = 0; i < counts.length; i++)
        {
            if(counts[i] > counts[maxIndex])
            {
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public String decrypt(String encrypted)
    {
        int[] frequent = countLetters(encrypted);
        int   maxIndex = indexOfMax(frequent);

        int dKey = maxIndex - 4;

        if(maxIndex < 4)
        {
            dKey = 26 - (4 - maxIndex);
        }

        return encrypt(encrypted, 26 - dKey);
    }

    public String halfOfString(String message, int start)
    {
        StringBuilder sb = new StringBuilder();

        for(int i = start; i < message.length(); i += 2)
        {
            char ch = message.charAt(i);
            sb.append(ch);
        }

        return sb.toString();
    }

    public int getKey(String s)
    {
        int[] frequent = countLetters(s);
        int   maxIndex = indexOfMax(frequent);
        int   dKey     = maxIndex - 4;

        if(maxIndex < 4)
        {
            dKey = 26 - (4 - maxIndex);
        }

        return dKey;
    }

    public String decryptTwoKeys(String encrypted)
    {
        String part1 = halfOfString(encrypted, 0);
        String part2 = halfOfString(encrypted, 1);

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);

        int key1 = getKey(part1);
        int key2 = getKey(part2);

        System.out.println("\nKey 1: " + key1);
        System.out.println("Key 2: " + key2);

        return encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }

    public String decryptTwoKeysWithKeys(String encrypted, int key1, int key2)
    {
        String part1 = halfOfString(encrypted, 0);
        String part2 = halfOfString(encrypted, 1);

        return encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }

    public void testDecrypt()
    {
        System.out.println("\n------------ With ONE key! -----------\n");
        System.out.println("\nPlain Message: "   + "Just a test string with lots of eeeeeeeeeeeeeeeees");
        System.out.println("Encrypted Message: " + encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees", 15));
        System.out.println("Decrypted Message: " + decrypt(encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees", 15)));

        System.out.println("\n\n------------ With TWO keys! -----------\n");
        System.out.println("\nPlain Message: "                 + "Just a test string with lots of eeeeeeeeeeeeeeeees");
        System.out.println("Encrypted Message: "               + encryptTwoKeys("Just a test string with lots of eeeeeeeeeeeeeeeees", 2, 24));
        System.out.println("Decrypted Message(with no keys): " + decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));
        System.out.println("Decrypted Message(with keys): "    + decryptTwoKeysWithKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 14, 24));

        FileResource fr       = new FileResource();
        String       content  = fr.asString();
        System.out.println("Decrypted Message(with no keys): " + decryptTwoKeys(content));
    }
}
