

import java.io.*;
import java.util.*;
import java.net.*;
import javax.crypto.*;
import java.security.*;



public class RC4CipherEntity 
{
 	 
      int[] cipherBox = new int [256];
      int[] cipherKeyArray = new int [256];
    
	  

   public String doEncryption(String cipherString, String mode)
  	
	
    {

		String doRC4 = "";
		String rc4Key = "&h01 &h23 &h45 &h89 &hab &hcd &hef";
	  	String rc4Data = cipherString;
		String rc4DataCiphered = "";
		String hexTableReturn ="";
		String stringReturn = "";
		char getAsciiChar;
		
	    
		
 	try
 	   {
 	   		 
		
		doRC4 = mode;
	  
		
		// Evaluate and call the appropriate encrypt
	
	
		if (doRC4.trim().equalsIgnoreCase("Encipher"))
			{
				
				   //RC4 Encipher Data!   
				   rc4DataCiphered = doCipher(rc4Key, rc4Data);
				   stringReturn =  rc4DataCiphered;			    
					
								
			}
		/*else if (doRC4.trim().equalsIgnoreCase("HexDump"))
			{
				
			
				      
				  rc4DataCiphered = rc4Data;
			
				   
				
				   
				  for(int i = 1; i < rc4DataCiphered.length(); i++)
					{
						getAsciiChar = rc4DataCiphered.charAt(i);
						//Hexidecimal output of encrypted string
						hexTableReturn = hexTableReturn + (Integer.toString(((byte) getAsciiChar & 0xff ) + 0x100, 16).substring( 1 ) + " "); 
						//out.println(getAsciiChar);
						
						if(i % 26 == 0)
						{
						 hexTableReturn = hexTableReturn + "<br>";
						}
					}
					
				 stringReturn = hexTableReturn;	
			}*/
				
						
      	 }

 	catch(Exception e)

		{
			
			System.out.println("Exception Thrown = " + e.toString());
			e.printStackTrace();
			
		}
	
		
	return stringReturn; 	
    
    
    } 	

private String doCipher(String cipherKey, String unencodedText)
{
	
  	// doCipher will encrypt unencrypted data 
     	int z = 0;
	int t = 0;
	int i = 0;
	int cipherBy = 0;
        int tempInt=0;
        String cipher = "";      
        char cipherText;

   	//Initialize cipherBox and cipherKeyArray  
    	doRC4MatrixSeed(cipherKey);
	
						    
	for(int a = 0; a < unencodedText.length(); a++)
		{
		//	System.out.println(" in line no 115 textlength"+unencodedText.length());
			i = (i + 1) % 255;
		//	System.out.println("in RC4CipherEntity  of SourceRouter in line no 117  "+i );
			t = (t + cipherBox[i]) % 255;
		//	System.out.println("in RC4CipherEntity  of SourceRouter in line no 119  "+t);
			tempInt = cipherBox[i];
			cipherBox[i] = cipherBox[t];
			cipherBox[t]= tempInt;

			z = cipherBox[(cipherBox[i] + cipherBox[t]) % 255];
		//	 System.out.println("in RC4CipherEntity  of SourceRouter in line no 125  "+z );
			//get character at position a	
			cipherText = unencodedText.charAt(a);
		//	 System.out.println("in RC4CipherEntity  of SourceRouter in line no 128  "+cipherText );
			//convert to ascii value XOR'd by z
			cipherBy = (int) cipherText ^z;
		//	System.out.println("in RC4CipherEntity  of SourceRouter in line no 131  "+cipherBy );
			//System.out.println("CipherBy=" + cipherBy);

			 cipher = cipher  + (char) cipherBy;
			// System.out.println("in RC4CipherEntity  of SourceRouter in line no 135  "+cipher );
			//System.out.println("CIPHER=" + cipher.toString());

   		}
      return cipher;

}


  private void doRC4MatrixSeed(String thisKey)
{

  
      //Initialize cipherBox and cipherKey Array's 
  
      int keyLength =0;
      int dataSwap;
      int b;
      int asciiVal=0;
      char asciiConvert;
      char asciiChar ; 
      keyLength = thisKey.length();
      
      for(int a = 0; a < 255; a++)
	{
	        //take the key character at the selected position	
		asciiChar = thisKey.charAt(a % keyLength);
	//	System.out.println("in RC4CipherEntity  of SourceRouter in line no 162  "+asciiChar );
		asciiVal = (int) asciiChar;
	//	System.out.println("in RC4CipherEntity  of SourceRouter in line no 164  "+asciiVal );
		cipherKeyArray[a] = asciiVal;
        	cipherBox[a]=a;
        
	 
	}

      b = 0;
      
      for(int a = 0; a < 255; a++)
	{
      
		 b = (b + cipherBox[a] + cipherKeyArray[a]) % 255;
	//	 System.out.println("in RC4CipherEntity  of SourceRouter in line no 177  "+b );
		 dataSwap = cipherBox[a];
		 cipherBox[a] = cipherBox[b];
		 cipherBox[b] = dataSwap;
	}
      
        
}




} //end of class
