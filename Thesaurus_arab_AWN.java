/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IR_Paraphrased;

import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import net.didion.jwnl.JWNLException;
import org.apache.lucene.analysis.ar.ArabicNormalizer;

/**
 *
 * @author Abeer
 */
public class Thesaurus_arab_AWN {
     static ArrayList syn1 ;
// //array of synonmous for lemma on orgQuery[1]
static ArrayList syn2 ;
// //array of synonmous for lemma on orgQuery[2]
static ArrayList syn3 ;
// //array of synonmous for lemma on orgQuery[3]
static ArrayList syn4 ;
   /* origquery is array of original query after it's tokneized and lemitized */
 ArrayList Enc_Syn1;
 ArrayList Enc_Syn2;
 ArrayList Enc_Syn3;
 ArrayList Enc_Syn4;
 Font arabicFont = new Font("Simplified Arabic", Font.PLAIN, 16);
    Thesaurus_arb a=new Thesaurus_arb();
    
    Thesaurus_arab_AWN(){
        //array of synonmous for lemma on orgQuery[0]
 syn1 = new ArrayList ();
// //array of synonmous for lemma on orgQuery[1]
 syn2 = new ArrayList ();
// //array of synonmous for lemma on orgQuery[2]
 syn3 = new ArrayList ();
// //array of synonmous for lemma on orgQuery[3]
 syn4 = new ArrayList ();
 
 //to encode the Synonyms arraylists into integer numbers
 Enc_Syn1=new ArrayList();
 Enc_Syn2=new ArrayList();
  Enc_Syn3=new ArrayList();
  
  Enc_Syn4=new ArrayList();
  
    
     try {
            int importdatabase=1;
            a.connectToDB(importdatabase);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(test_arabic_thesurus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public void set_Syn1(String orgQuery_W) 
{
    
    
          ArabicNormalizer normalizer=new ArabicNormalizer();
          syn1.add(orgQuery_W);
            // Try to get a Synonym for any IndexWord, first come first serve!
            if(!" ".equals(orgQuery_W)){
          
        ArrayList <String> synId ;
        synId = new ArrayList ();
    
         
          synId.addAll( a.getSynset_noDiacritics(orgQuery_W));
       ArrayList related_to=new ArrayList();
          
      
       for(int i=0; i<synId.size();i++)
      {
     related_to.addAll(  a.getSynonm(synId.get(i)));
     //  related_to.addAll(  a.getHyponyms(synId.get(i)));
      }
      ArrayList value=new ArrayList();
      for(int i=0; i<related_to.size();i++)
      {
     value.addAll(  a.readSynsetMembers((String)related_to.get(i)));
      }
           
      for(int y=0;y<value.size();y++)
      {
           String text= (String) value.get(y);
          
          char[]s;
        s = new char[text.length()];
      s=text.toCharArray();
    int len=  normalizer.normalize(s, s.length); 
    String str = "";
    for(int r=0;r<len;r++){
      str =  str  + s[r];   
    }
    syn1.add(str);
            }
            }
            syn1=remove_dublict(syn1); 
            Enc_Syn1=EncodetheSynonymlist(syn1.size(),1);

          
    
// 55555555555555555555555555555555555555555555555555555555555555555555555555   
//       ArabicNormalizer normalizer=new ArabicNormalizer();
//          syn1.add(orgQuery_W);
//            // Try to get a Synonym for any IndexWord, first come first serve!
//            if(!" ".equals(orgQuery_W)){
//          
//        ArrayList <String> synId ;
//        synId = new ArrayList ();
//    
//         
//          synId.addAll( a.getSynset_noDiacritics(orgQuery_W));
////       ArrayList related_to=new ArrayList();
//       ArrayList value=new ArrayList();   
//      
//       for(int i=0; i<synId.size();i++)
//      {
//     
//      
//     value.addAll(  a.readSynsetMembers((String)synId.get(i)));
//      }
//           
//      for(int y=0;y<value.size();y++)
//      {
//           String text= (String) value.get(y);
//          
//          char[]s;
//        s = new char[text.length()];
//      s=text.toCharArray();
//    int len=  normalizer.normalize(s, s.length); 
//    String str = "";
//    for(int r=0;r<len;r++){
//      str =  str  + s[r];   
//    }
//    syn1.add(str);
//            }
//            }
//            syn1=remove_dublict(syn1); 
//            Enc_Syn1=EncodetheSynonymlist(syn1.size(),1);

          
    
//    ----------------------------------------------------------------------
//      ArabicNormalizer normalizer=new ArabicNormalizer();
//          syn1.add(orgQuery_W);
//            // Try to get a Synonym for any IndexWord, first come first serve!
//            if(!" ".equals(orgQuery_W)){
//          
//        ArrayList <String> synId ;
//        synId = new ArrayList ();
//    
//         
//          synId.addAll( a.getSynset_noDiacritics(orgQuery_W));
//       ArrayList related_to=new ArrayList();
//          
//      
//       for(int i=0; i<synId.size();i++)
//      {
//     related_to.addAll(  a.getSynonm(synId.get(i)));
//      }
//      ArrayList value=new ArrayList();
//      for(int i=0; i<related_to.size();i++)
//      {
//     value.addAll(  a.readSynsetMembers((String)related_to.get(i)));
//      }
//           
//      for(int y=0;y<value.size();y++)
//      {
//           String text= (String) value.get(y);
//          
//          char[]s;
//        s = new char[text.length()];
//      s=text.toCharArray();
//    int len=  normalizer.normalize(s, s.length); 
//    String str = "";
//    for(int r=0;r<len;r++){
//      str =  str  + s[r];   
//    }
//    syn1.add(str);
//            }
//            }
//            syn1=remove_dublict(syn1); 
//            Enc_Syn1=EncodetheSynonymlist(syn1.size(),1);
//
//          
}  //end func
  
    public void close(){
    a.closeConnection();
    } 
    
    public   ArrayList get_syn1()
{
    return syn1;
}

    public void set_Syn2(String orgQuery_W) 
{
    ArabicNormalizer normalizer=new ArabicNormalizer();
          syn2.add(orgQuery_W);
            // Try to get a Synonym for any IndexWord, first come first serve!
            if(!" ".equals(orgQuery_W)){
          
        ArrayList <String> synId ;
        synId = new ArrayList ();
    
         
          synId.addAll( a.getSynset_noDiacritics(orgQuery_W));
       ArrayList related_to=new ArrayList();
          
      
       for(int i=0; i<synId.size();i++)
      {
     related_to.addAll(  a.getSynonm(synId.get(i)));
       //related_to.addAll(  a.getHyponyms(synId.get(i)));
      }
      ArrayList value=new ArrayList();
      for(int i=0; i<related_to.size();i++)
      {
     value.addAll(  a.readSynsetMembers((String)related_to.get(i)));
      }
           
      for(int y=0;y<value.size();y++)
      {
           String text= (String) value.get(y);
          
          char[]s;
        s = new char[text.length()];
      s=text.toCharArray();
    int len=  normalizer.normalize(s, s.length); 
    String str = "";
    for(int r=0;r<len;r++){
      str =  str  + s[r];   
    }
    syn2.add(str);
            }
            }
            syn2=remove_dublict(syn2); 
            Enc_Syn2=EncodetheSynonymlist(syn2.size(),1);

          
//    555555555555555555555555555555555555555555555555555555555555555
//      
//       ArabicNormalizer normalizer=new ArabicNormalizer();
//          syn2.add(orgQuery_W);
//            // Try to get a Synonym for any IndexWord, first come first serve!
//            if(!" ".equals(orgQuery_W)){
//          
//        ArrayList <String> synId ;
//        synId = new ArrayList ();
//    
//         
//          synId.addAll( a.getSynset_noDiacritics(orgQuery_W));
////       ArrayList related_to=new ArrayList();
//       ArrayList value=new ArrayList();   
//      
//       for(int i=0; i<synId.size();i++)
//      {
//     
//      
//     value.addAll(  a.readSynsetMembers((String)synId.get(i)));
//      }
//           
//      for(int y=0;y<value.size();y++)
//      {
//           String text= (String) value.get(y);
//          
//          char[]s;
//        s = new char[text.length()];
//      s=text.toCharArray();
//    int len=  normalizer.normalize(s, s.length); 
//    String str = "";
//    for(int r=0;r<len;r++){
//      str =  str  + s[r];   
//    }
//    syn2.add(str);
//            }
//            }
//            syn2=remove_dublict(syn2); 
//            Enc_Syn2=EncodetheSynonymlist(syn2.size(),1);

          
    
//    --------------------------------------------------------------------
//      ArabicNormalizer normalizer=new ArabicNormalizer();
//          syn2.add(orgQuery_W);
//            // Try to get a Synonym for any IndexWord, first come first serve!
//            if(!" ".equals(orgQuery_W)){
//          
//        ArrayList <String> synId ;
//        synId = new ArrayList ();
//    
//         
//          synId.addAll( a.getSynset_noDiacritics(orgQuery_W));
//      
//     ArrayList related_to=new ArrayList();
//          
//      
//       for(int i=0; i<synId.size();i++)
//      {
//     related_to.addAll(  a.getSynonm(synId.get(i)));
//      }
//      ArrayList value=new ArrayList();
//      for(int i=0; i<related_to.size();i++)
//      {
//     value.addAll(  a.readSynsetMembers((String)related_to.get(i)));
//      }
//           
//           
//      for(int y=0;y<value.size();y++)
//      {
//           String text= (String) value.get(y);
//          
//          char[]s;
//        s = new char[text.length()];
//      s=text.toCharArray();
//    int len=  normalizer.normalize(s, s.length); 
//    String str = "";
//    for(int r=0;r<len;r++){
//      str =  str  + s[r];   
//    }
//    syn2.add(str);
//            }
//            }
//            syn2=remove_dublict(syn2); 
//            Enc_Syn2=EncodetheSynonymlist(syn2.size(),1);

          
}  //end func
    
    
      public void set_Syn3(String orgQuery_W) 
{
    ArabicNormalizer normalizer=new ArabicNormalizer();
          syn3.add(orgQuery_W);
            // Try to get a Synonym for any IndexWord, first come first serve!
            if(!" ".equals(orgQuery_W)){
          
        ArrayList <String> synId ;
        synId = new ArrayList ();
    
         
          synId.addAll( a.getSynset_noDiacritics(orgQuery_W));
       ArrayList related_to=new ArrayList();
          
      
       for(int i=0; i<synId.size();i++)
      {
     related_to.addAll(  a.getSynonm(synId.get(i)));
      // related_to.addAll(  a.getHyponyms(synId.get(i)));
      }
      ArrayList value=new ArrayList();
      for(int i=0; i<related_to.size();i++)
      {
     value.addAll(  a.readSynsetMembers((String)related_to.get(i)));
      }
           
      for(int y=0;y<value.size();y++)
      {
           String text= (String) value.get(y);
          
          char[]s;
        s = new char[text.length()];
      s=text.toCharArray();
    int len=  normalizer.normalize(s, s.length); 
    String str = "";
    for(int r=0;r<len;r++){
      str =  str  + s[r];   
    }
    syn3.add(str);
            }
            }
            syn3=remove_dublict(syn3); 
            Enc_Syn3=EncodetheSynonymlist(syn3.size(),1);

          
//      55555555555555555555555555555555555555555555555555555555555555555
//       ArabicNormalizer normalizer=new ArabicNormalizer();
//          syn3.add(orgQuery_W);
//            // Try to get a Synonym for any IndexWord, first come first serve!
//            if(!" ".equals(orgQuery_W)){
//          
//        ArrayList <String> synId ;
//        synId = new ArrayList ();
//    
//         
//          synId.addAll( a.getSynset_noDiacritics(orgQuery_W));
////       ArrayList related_to=new ArrayList();
//       ArrayList value=new ArrayList();   
//      
//       for(int i=0; i<synId.size();i++)
//      {
//     
//      
//     value.addAll(  a.readSynsetMembers((String)synId.get(i)));
//      }
//           
//      for(int y=0;y<value.size();y++)
//      {
//           String text= (String) value.get(y);
//          
//          char[]s;
//        s = new char[text.length()];
//      s=text.toCharArray();
//    int len=  normalizer.normalize(s, s.length); 
//    String str = "";
//    for(int r=0;r<len;r++){
//      str =  str  + s[r];   
//    }
//    syn3.add(str);
//            }
//            }
//            syn3=remove_dublict(syn3); 
//            Enc_Syn3=EncodetheSynonymlist(syn3.size(),1);
//
//          
    
//    -------------------------------------------------------------------
//      ArabicNormalizer normalizer=new ArabicNormalizer();
//          syn3.add(orgQuery_W);
//            // Try to get a Synonym for any IndexWord, first come first serve!
//            if(!" ".equals(orgQuery_W)){
//          
//        ArrayList <String> synId ;
//        synId = new ArrayList ();
//    
//         
//          synId.addAll( a.getSynset_noDiacritics(orgQuery_W));
//      
//     ArrayList related_to=new ArrayList();
//          
//      
//       for(int i=0; i<synId.size();i++)
//      {
//     related_to.addAll(  a.getSynonm(synId.get(i)));
//      }
//      ArrayList value=new ArrayList();
//      for(int i=0; i<related_to.size();i++)
//      {
//     value.addAll(  a.readSynsetMembers((String)related_to.get(i)));
//      }
//           
//      for(int y=0;y<value.size();y++)
//      {
//           String text= (String) value.get(y);
//          
//          char[]s;
//        s = new char[text.length()];
//      s=text.toCharArray();
//    int len=  normalizer.normalize(s, s.length); 
//    String str = "";
//    for(int r=0;r<len;r++){
//      str =  str  + s[r];   
//    }
//    syn3.add(str);
//            }
//            }
//            syn3=remove_dublict(syn3); 
//            Enc_Syn3=EncodetheSynonymlist(syn3.size(),1);
//
//          
}  //end func
      
      public void set_Syn4(String orgQuery_W) 
{
    
    ArabicNormalizer normalizer=new ArabicNormalizer();
          syn4.add(orgQuery_W);
            // Try to get a Synonym for any IndexWord, first come first serve!
            if(!" ".equals(orgQuery_W)){
          
        ArrayList <String> synId ;
        synId = new ArrayList ();
    
         
          synId.addAll( a.getSynset_noDiacritics(orgQuery_W));
       ArrayList related_to=new ArrayList();
          
      
       for(int i=0; i<synId.size();i++)
      {
     related_to.addAll(  a.getSynonm(synId.get(i)));
      // related_to.addAll(  a.getHyponyms(synId.get(i)));
      }
      ArrayList value=new ArrayList();
      for(int i=0; i<related_to.size();i++)
      {
     value.addAll(  a.readSynsetMembers((String)related_to.get(i)));
      }
           
      for(int y=0;y<value.size();y++)
      {
           String text= (String) value.get(y);
          
          char[]s;
        s = new char[text.length()];
      s=text.toCharArray();
    int len=  normalizer.normalize(s, s.length); 
    String str = "";
    for(int r=0;r<len;r++){
      str =  str  + s[r];   
    }
    syn4.add(str);
            }
            }
            syn4=remove_dublict(syn4); 
            Enc_Syn4=EncodetheSynonymlist(syn4.size(),1);

          
//      5555555555555555555555555555555555555555555555555555555555555
//       ArabicNormalizer normalizer=new ArabicNormalizer();
//          syn4.add(orgQuery_W);
//            // Try to get a Synonym for any IndexWord, first come first serve!
//            if(!" ".equals(orgQuery_W)){
//          
//        ArrayList <String> synId ;
//        synId = new ArrayList ();
//    
//         
//          synId.addAll( a.getSynset_noDiacritics(orgQuery_W));
////       ArrayList related_to=new ArrayList();
//       ArrayList value=new ArrayList();   
//      
//       for(int i=0; i<synId.size();i++)
//      {
//     
//      
//     value.addAll(  a.readSynsetMembers((String)synId.get(i)));
//      }
//           
//      for(int y=0;y<value.size();y++)
//      {
//           String text= (String) value.get(y);
//          
//          char[]s;
//        s = new char[text.length()];
//      s=text.toCharArray();
//    int len=  normalizer.normalize(s, s.length); 
//    String str = "";
//    for(int r=0;r<len;r++){
//      str =  str  + s[r];   
//    }
//    syn4.add(str);
//            }
//            }
//            syn4=remove_dublict(syn4); 
//            Enc_Syn4=EncodetheSynonymlist(syn4.size(),1);
//
//          
//    
    
//    -------------------------------------------------------------------------
//    ArabicNormalizer normalizer=new ArabicNormalizer();
//          syn4.add(orgQuery_W);
//            // Try to get a Synonym for any IndexWord, first come first serve!
//            if(!" ".equals(orgQuery_W)){
//          
//        ArrayList <String> synId ;
//        synId = new ArrayList ();
//    
//         
//          synId.addAll( a.getSynset_noDiacritics(orgQuery_W));
//      
//      ArrayList related_to=new ArrayList();
//          
//      
//       for(int i=0; i<synId.size();i++)
//      {
//     related_to.addAll(  a.getSynonm(synId.get(i)));
//      }
//      ArrayList value=new ArrayList();
//      for(int i=0; i<related_to.size();i++)
//      {
//     value.addAll(  a.readSynsetMembers((String)related_to.get(i)));
//      }
//           
//           
//      for(int y=0;y<value.size();y++)
//      {
//           String text= (String) value.get(y);
//          
//          char[]s;
//        s = new char[text.length()];
//      s=text.toCharArray();
//    int len=  normalizer.normalize(s, s.length); 
//    String str = "";
//    for(int r=0;r<len;r++){
//      str =  str  + s[r];   
//    }
//    syn4.add(str);
//            }
//            }
//            syn4=remove_dublict(syn4); 
//            Enc_Syn4=EncodetheSynonymlist(syn4.size(),1);
//
//          
}  //end func
    public   ArrayList get_syn3()
{
    return syn3;
}  
    
     public   ArrayList get_syn2()
{
    return syn2;
}
    
  public   ArrayList get_syn4()
{
    return syn4;
}

 public void set_syn4(ArrayList l){
  syn4.addAll(l);   
 }  
  public void set_syn3(ArrayList l){
  syn3.addAll(l);   
 }  
   public void set_syn2(ArrayList l){
  syn2.addAll(l);   
 }  
   public void set_syn1(ArrayList l){
  syn1.addAll(l);   
 }  
   
    public static ArrayList EncodetheSynonymlist(int length ,int ithsyn_list)  {
        
        ArrayList Encoded_Syn=new ArrayList();
        //if the list is syn1 then 
        if(ithsyn_list==1)
        {
        for(int i=1;i<=length;i++)
        {
         Encoded_Syn.add(i+1000);   
        }//end for syn1 encoding list 
        }//end of if i=1
        
         //if the list is syn2 then 
        if(ithsyn_list==2)
        {
        for(int i=1;i<=length;i++)
        {
         Encoded_Syn.add(i+2000);   
        }//end for syn3 encoding list 
        }//end of if i=3
        
        if(ithsyn_list==3)
        {
        for(int i=1;i<=length;i++)
        {
         Encoded_Syn.add(i+3000);   
        }//end for syn3 encoding list 
        }//end of if i=3
        
        if(ithsyn_list==4)
        {
        for(int i=1;i<=length;i++)
        {
         Encoded_Syn.add(i+4000);   
        }//end for syn4 encoding list 
        }//end of if i=4
        
        return Encoded_Syn;
    }//end of  EncodetheSynonymlist method  
     
    public  String lookUp_syn(int i)
    {
        String lemma="";
        //or try this decimal a = 1.56M;
//string f = a.ToString();

//f=f.Substring( f.LastIndexOf('.',f.Length));
        //double Syn_list_number =  Math.floor(i * 0.001);
       
        
        int Syn_list_number = (int) (i * 0.001);
        
        
        int index =i-(Syn_list_number*1000);
         
      
        
        if(Syn_list_number==1)
        {
           lemma= syn1.get(index-1).toString();  
        }//end syn1
        if(Syn_list_number==2)
        {
          lemma= syn2.get(index-1).toString();  
        }//end syn2
        if(Syn_list_number==3)
        {
            
           lemma= syn3.get(index-1).toString();  
        }//end syn3
        
        if(Syn_list_number==4)
        {
           
        
         lemma= syn4.get(index-1).toString();  
        }//end syn4
      
     return    lemma;
     
    }//end llokup
    
     public static ArrayList remove_dublict(ArrayList arrayList1)
  {
 int q,z;     
for(q = 0; arrayList1!=null && q < arrayList1.size() ; q++) { 
    String w=arrayList1.get(q).toString().toLowerCase();
    for(z = 0; arrayList1!=null && z < arrayList1.size(); z++) {
        if(w.equals(arrayList1.get(z).toString().toLowerCase())&& z !=q) {
            arrayList1.remove(z);
        }
    }
    
}
return arrayList1;
  }
    
}//end class
