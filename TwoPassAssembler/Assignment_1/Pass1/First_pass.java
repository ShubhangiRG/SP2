package pass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;


public class First_pass {
	
	public static void main(String args[]) throws IOException
	{
		try
		{
            File file = new File("code.txt");
            File file1 = new File("pnemonics.txt");
            File file2 = new File("inter.txt");
            File file3 = new File("literal.txt");
            File file4 = new File("symbol.txt");
            File file5 = new File("pool_table.txt");
            
            FileReader fr = new FileReader(file);
            FileReader fr1 = new FileReader(file1);

            FileWriter fw = new FileWriter(file2);
            FileWriter fw1 = new FileWriter(file3);
            FileWriter fw2 = new FileWriter(file4);
            FileWriter fw3 = new FileWriter(file5);

            BufferedReader br = new BufferedReader(fr);
            BufferedReader br1 = new BufferedReader(fr1);
            
            BufferedWriter bw = new BufferedWriter(fw);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            BufferedWriter bw3 = new BufferedWriter(fw3);
            
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer1 = new StringBuffer();
            
            String line,str,str1;
            
            String token[]=new String[1024];
            String mnem[]=new String[1024];
            String literal[]=new String[1024];
            String symbol[]=new String[1024];
            String pool[]=new String[1024];

            
            int code,lit=1,sym=1,mn,cnt=0;
			String abc="";
            int flag=0,flg=0,f=0,f1=0,f2=0,f3=0;

            while ((line = br.readLine()) != null)
            {
                    stringBuffer.append(line);
                    stringBuffer.append("\n");
            }
            StringTokenizer it = new StringTokenizer(stringBuffer.toString(),"	,\n");
            code=it.countTokens();
            for(int i=0;i<code;i++)
            {
         	   token[i]=it.nextToken();
            }

            while ((line = br1.readLine()) != null)
            {
                    stringBuffer1.append(line);
                    stringBuffer1.append("\n");
            }
            StringTokenizer mt = new StringTokenizer(stringBuffer1.toString(),"	\n");
            mn=mt.countTokens();
            for(int i=0;i<mn;i++)
            {
         	   mnem[i]=mt.nextToken();
            }
            
            int l=0,s=0,p=0;
            
            
 //=====================================================================================================
            int x=0;
            while(x!=code)
            {
            	f1=0;
            	str=token[x++];
            	if(str.equals("START"))
            	{
            		str=token[x++];
            		cnt=Integer.parseInt(str);
            	}
            	else
            	{
            		//search in mnemonic table
            		if(str.equals("LTORG") || str.equals("END"))
            		{
            			f1=0;
            			f2=1;
            			flg=0;
            			if(str.equals("LTORG"))
            			{
            				bw.write("	(AD,05)\n");
            			}
            			else
            			{
            				f1=1;
            				bw.write("	(AD,02)\n");
            			}
            			str=token[x++];
            			while(str.startsWith("="))
            			{
                			for(int i=0;i<l;i++)
                			{
                				if(str.equals(literal[i]))
                				{            				

                					if(literal[i+1].equals("?"))
                					{
                    					if(flg==0)
                    						pool[p++]="#"+literal[i-1];
                    					flg=1;
                        				bw.write(cnt+"		"+literal[i]+"\n");
                    					literal[i+1]=Integer.toString(cnt);
                    					cnt++;                						
                					}
                				}
                			}  
                				str=token[x++];
            			}

            		}
            		
            		if(str.endsWith(":"))
            		{
            			flg=0;
            			for(int i=0;i<s;i++)
            			{
            				if(str.substring(0,str.length()-1).equals(symbol[i]))
            				{
            					flg=1;
            				}
            			}
            			if(flg==0)
            			{
                			symbol[s++]=Integer.toString(sym++);
                    		symbol[s++]=str.substring(0,str.length()-1);
                    		symbol[s++]=Integer.toString(cnt);       				
            			}
            		}
            		
            		else if(str.equals("ORIGIN"))
            		{
        				str=token[x++];
        				bw.write("	(AD,03)	"+str+"\n");
            			it=new StringTokenizer(str,"+");
            			str=it.nextToken();
            			for(int i=0;i<s;i++)
            			{
            				if(str.equals(symbol[i]))
            				{
            					cnt=Integer.parseInt(symbol[i+1]);
            					break;
            				}
            			}
            			str=it.nextToken();
            			cnt=cnt+Integer.parseInt(str);
            		}
            		else if(str.equals("EQU"))
            		{
            			str=token[x-2];
            			str1=str.substring(0,str.length()-1);
            			str=token[x++];
            			bw.write("	(AD,04)	"+str+"\n");
            			for(int i=0;i<s;i++)
            			{
            				if(str.equals(symbol[i]))
            				{
            					abc=symbol[i+1];
            					break;
            				}
            			}
            			for(int i=0;i<s;i++)
            			{
            				if(str1.equals(symbol[i]))
            				{
            					symbol[i+1]=abc;
            					break;
            				}
            			}
            		}
            		else if(str.equals("DS"))
            		{
            			str=token[x++];
            			bw.write(cnt+"	(DL,02)	"+str+"\n");
            			cnt=cnt+Integer.parseInt(str);
            		}
            		else
            		{
            			//check in mnemonic table
                    	for(int j=0;j<mn;j++)
                    	{
                    		if(str.equals(mnem[j]))
                    		{
                    			f1=0;
                    			if(!str.equals("STOP"))
                    			{
                        			bw.write(cnt+"	(IS,"+mnem[j+1]+")	");
                        			cnt=cnt+Integer.parseInt(mnem[j+2]);
                    				str=token[x++];
                    			}
                    			else
                    			{
                        			bw.write(cnt+"	(IS,"+mnem[j+1]+")	"+"\n");
                        			cnt=cnt+Integer.parseInt(mnem[j+2]);
                    				f1=1;
                    			}
                    			break;
                    		}
                    	}
                    	
                    	//check if register 
                    	if(str.equals("AREG") || str.equals("BREG") || str.equals("CREG"))
                    	{
                    		bw.write(str+" ");
                    	}               	
                    	//for literal
                    	else if(str.startsWith("="))
                    	{
                    		flg=0;
                    		if(flag==0)
                    		{
                    			literal[l++]=Integer.toString(lit++);
                        		literal[l++]=str;
                        		literal[l++]="?";
                        		flag=1;
                    		}
                    		for(int j=0;j<l;j++)
                    		{
                    			if(str.equals(literal[j]) && f2==0)
                    			{
                    				bw.write("(L,"+literal[j-1]+")"+"\n");
                    				flg=1;
                    			}
                    		}
                    		if(flg==0 || f2==1)
                    		{
                    			literal[l++]=Integer.toString(lit++);
                        		literal[l++]=str;
                        		literal[l++]="?";        
                				bw.write("(L,"+literal[l-3]+")"+"\n");
                    		}
                    	}
                    	//adding in symbol table
                    	else if(f1==0)
                    	{
                    		bw.write(str+"\n");
                    	}
                
                	}
            	}
            		
            }
            
            for(int i=0;i<l;i++)
            {
            	bw1.write(literal[i++]+"	"+literal[i++]+"	"+literal[i]+"\n");
            }
            for(int i=0;i<s;i++)
            {
            	bw2.write(symbol[i++]+"	"+symbol[i++]+"	"+symbol[i]+"\n");
            }
            for(int i=0;i<p;i++)
            	bw3.write(pool[i]+"\n");
            
            bw.close();
            bw1.close();
            bw2.close();
            bw3.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}