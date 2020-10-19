import java.util.*;
import java.lang.*;


public class decoder
{
  public static void main(String args[])
  {
    String method = "";
    String message = "";
    String key = "";

    if (0 < args.length)
    {
        method = new String(args[0]);
        message = new String(args[1]);
        key = new String(args[2]);
    }
    else
    {
    System.err.println("Invalid arguments count:" + args.length);
    System.exit(0);
    }

        message = message.toUpperCase();
        key = key.toUpperCase();
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
