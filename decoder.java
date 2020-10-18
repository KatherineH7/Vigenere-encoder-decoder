import java.util.*;
import java.lang.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class decoder
{
  public static void main(String args[])
  {
    File plaintext = null;
    File keytext = null;
    String method = "";
    String message = "";
    String key = "";

    if (0 < args.length)
    {
        method = new String(args[0]);
        plaintext = new File(args[1]);
        keytext = new File(args[2]);
    }
    else
    {
    System.err.println("Invalid arguments count:" + args.length);
    System.exit(0);
    }

      BufferedReader br = null;

      try
      {
        String sCurrentLine;

        br = new BufferedReader(new FileReader(plaintext));

        while ((sCurrentLine = br.readLine()) != null)
          {
            message += sCurrentLine;
          }

          BufferedReader br2 = null;

         br2 = new BufferedReader(new FileReader(keytext));

         while ((sCurrentLine = br2.readLine()) != null)
           {
             key += sCurrentLine;
           }

        }

       catch (IOException e)
        {
          e.printStackTrace();
        }

        finally
        {
          try
          {
            if (br != null)br.close();
          }
          catch (IOException ex)
          {
            ex.printStackTrace();
          }
        }

        message = message.toUpperCase();
        message = message.replaceAll("\\p{Punct}", "");
        message = message.replaceAll("\\d","");
        message = message.replaceAll(" ","");

        boolean shift = true;
        if (method.equals("decode"))
          shift = false;

        System.out.println(Vigenere(message, key, shift));

      }



  static String Vigenere(String text, String key, Boolean encode)
  {
    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    int a = 0;
    int b = 0;
    String result = "";
    for (int c = 0; c < text.length(); c++)
    {
      for (int i = 0; i < alphabet.length; i++)
      {
        if (alphabet[i] == text.charAt(c))
            a = i;
      }
      for (int i = 0; i < alphabet.length; i++)
      {
        if (alphabet[i] == key.charAt(c % (key.length())))
            b = i;
      }
      if (encode == false)
        result += alphabet[(a - b + 26) % 26];
      else
        result += alphabet[(a + b) % 26];
    }
    return result;
  }
}
