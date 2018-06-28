/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IR_Paraphrased;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jenes.GeneticAlgorithm;
import jenes.chromosome.ObjectChromosome;
import jenes.population.Individual;
import net.didion.jwnl.JWNLException;
import org.apache.lucene.analysis.ar.ArabicAnalyzer;
import org.apache.lucene.analysis.shingle.ShingleAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author Abeer
 */
public class GA_Fitness_ar extends GeneticAlgorithm<ObjectChromosome>  {
     private double wight;
    private ArrayList ParaphrasedQuery = new ArrayList();// 
    private Directory d;
//    private int populationsize;
   private int querylength;
   ArrayList fitness=new ArrayList();
   
   private ArrayList ABC_ParaphrasedQuery= new ArrayList();
            private ArrayList ABC_fitness= new ArrayList();
//    private int[][] fitness_array=new int[populationsize][querylength];
    int r = 0;
    FileWriter fitness_file;//= new FileWriter(new File("C:\\fitness\\fitness_ga.txt"));
    FileReader fitness_file_Read;//== new FileReader(new File("C:\\fitness\\fitness_ga.txt"));
    Scanner input;
    static Thesaurus_arab_AWN S;

    public void GA_Fitness_ar() {
        try {
            fitness_file = new FileWriter(new File("C:\\fitness\\fitness_ga.txt"));
            fitness_file_Read = new FileReader(new File("C:\\fitness\\fitness_ga.txt"));
           
        } catch (IOException ex) {
            Logger.getLogger(GA_Fitness.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void set_query_length(int q){
     querylength=   q;
    }
//public void set_query_length_population_size(int q,int p)
//{
//   populationsize=p;
//   querylength=q;
//   fitness_array=new int[populationsize][querylength]; 
//    
//}
    public double calculate_fitness(String w1,String w2)  {
        double w = -1;//if w==-1 then there is no frequency for it in file ==freq_calculated=false
        //here I want to check if the frequence has been claculated by searching the content of file 
        String line=null;
        try {
            // FileWriter fitness_file= new FileWriter(new File("C:\\fitness\\fitness_ga.txt"));
             fitness_file_Read = new FileReader(new File("C:\\fitness\\fitness_ga.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GA_Fitness.class.getName()).log(Level.SEVERE, null, ex);
        }
         input=new Scanner(fitness_file_Read);
            while(input.hasNextLine()){
                
                line=input.nextLine();
                Scanner scan=new Scanner(line);
                scan.useDelimiter("-");
                if(scan.hasNext()){
                   String first_w=scan.next();
                   String second_w=scan.next();
                   String fitness_value= scan.next();
//                   System.out.println("first_w  "+first_w +"  w1  "+w1);
//                    System.out.println("second_w  "+second_w +"  w2 "+w2);
//                    System.out.println("Fintens value  "+fitness_value);
                  if(first_w.equals(w1) && second_w.equals(w2) ||first_w.equals(w2) && second_w.equals(w1) )  
                  {
                      w=Double.valueOf(fitness_value);
                      break;
                  }//end if 
                }
            
               
            }//end while
        input.close();
        try {
            fitness_file_Read.close();
        } catch (IOException ex) {
            Logger.getLogger(GA_Fitness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w;
    }

    public void Set_thesaurus(Thesaurus_arab_AWN g,ArrayList l1, ArrayList l2, ArrayList l3, ArrayList l4) throws JWNLException, FileNotFoundException, IOException {
        S = g;

//        S.set_syn1(l1);
//        S.set_syn2(l2);
//        S.set_syn3(l3);
//        S.set_syn4(l4);
      //  System.out.println("size of S " + S.get_syn1().size());
        //S=a;

    }

    public void set_Characters() throws IOException {
        // 0. Specify the analyzer for tokenizing text.
        //    The same analyzer should be used for indexing and searching
        ArabicAnalyzer analyzer = new ArabicAnalyzer(Version.LUCENE_35);
        ShingleAnalyzerWrapper shingleAnalyzer = new ShingleAnalyzerWrapper(analyzer, 2);
        shingleAnalyzer.setOutputUnigrams(false);
        //**************new indexer start***********************
        //fileDir the directory that contains thefiles to be indexed
        File fileDir = new File("C:\\files_to_index_arb");

        //indexDir is the directory that hosts Lucene's index files
        File indexDir = new File("C:\\luceneIndex_Shingle_ar");

        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35, shingleAnalyzer);
// To store an index on disk 
        d = FSDirectory.open(indexDir);

        r = 0;

        if (r == 1) {
            try (IndexWriter indexWriter = new IndexWriter(d, config)) {
                File[] textFiles = fileDir.listFiles();
                // System.out.println("txtlength"+textFiles.length);
                for (int i = 0; i < textFiles.length; i++) {
                    if (textFiles[i].isFile() && textFiles[i].getName().endsWith(".txt")) // if(textFiles[i].isFile())
                    {

                        Reader textReader = new FileReader(textFiles[i]);
                        Document document = new Document();
                        document.add(new Field("content", textReader));
                        document.add(new Field("path", textFiles[i].getPath(), Field.Store.YES, Field.Index.ANALYZED));
                        // System.out.println("Ith doc "+i);
                        indexWriter.addDocument(document);
                    }
                }//end for txtfile
                indexWriter.optimize();

            }//try index writer 
        }//if r==0


//      System.out.print("r="+r); 
//       System.out.println("It took " 
//              + " milliseconds to create an index for the files in the directory "
//              + fileDir.getPath());
    }//end set char

    public void paraphraseFitness() throws IOException {
        // 0. Specify the analyzer for tokenizing text.
        //    The same analyzer should be used for indexing and searching
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
        //  System.out.println("am in parafunction construct");
        ShingleAnalyzerWrapper shingleAnalyzer = new ShingleAnalyzerWrapper(analyzer, 2);
        shingleAnalyzer.setOutputUnigrams(false);
        //**************new indexer start***********************
        //fileDir the directory that contains thefiles to be indexed
        File fileDir = new File("C:\\files_to_index_arb");

        //indexDir is the directory that hosts Lucene's index files
        File indexDir = new File("C:\\luceneIndex_Shingle_ar");


        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35, shingleAnalyzer);
// To store an index on disk 
        d = FSDirectory.open(indexDir);


        // int r=0 ;
        if (r == 1) {
            try (IndexWriter indexWriter = new IndexWriter(d, config)) {
                File[] textFiles = fileDir.listFiles();

                for (int i = 0; i < textFiles.length; i++) {
                    if (textFiles[i].isFile() && textFiles[i].getName().endsWith(".txt")) // if(textFiles[i].isFile())
                    {

                        Reader textReader = new FileReader(textFiles[i]);
                        Document document = new Document();
                        document.add(new Field("content", textReader));
                        document.add(new Field("path", textFiles[i].getPath(), Field.Store.YES, Field.Index.ANALYZED));

                        indexWriter.addDocument(document);
                    }
                }//end for txtfile
                indexWriter.optimize();

            }//try index writer 
        }//if r==0



    }//end of initialize parameter 

    public void setWight(double w) {
        this.wight = w;

    }//end set wight

    public void setParaphrasedQuery(ArrayList q) {
        this.ParaphrasedQuery = q;

    }//end set para

    /**
     * Fitness function. A lower value value means the difference between the
     * total volume of boxes in a van is small, which is better. This means a
     * more optimal distribution of boxes in the vans. The number of vans needed
     * is multiplied by the size difference as more vans are more expensive.
     */
    @Override
    public void evaluateIndividual(Individual<ObjectChromosome> individual) {
 double found_previous_fit=-1;
        ObjectChromosome a_subject = individual.getChromosome();
        int[] query=new int[a_subject.length()];
       for( int u=0;u<a_subject.length();u++)
       {
        query[u]= (int)a_subject.getValue(u) ; 
       }
       found_previous_fit=search_Previous_fitness(query);
        if( found_previous_fit== -1)
        {
        double w = 1;
        int coun = 0;
        ArrayList w_val = new ArrayList();

        IndexReader ir;

        File indexDir = new File("C:\\luceneIndex_arb");



        try {
            d = FSDirectory.open(indexDir);
            ir = IndexReader.open(d);
            Double tmp = 0.0;
            int final_count_freq = 0;
            // start applying joint probability ( frequence )on the chromosome genes

            int gene_i = 0;


            int chromosome_Length = a_subject.length();
           // System.out.println("chro length   "+chromosome_Length);
            for (int x = 0; x < chromosome_Length; x++) {
                gene_i++;

                String word_i = S.lookUp_syn((int) a_subject.getValue(x));

                for (int y = x; y < chromosome_Length - gene_i; y++) {    

                    String word_iPlus1 = S.lookUp_syn((int) a_subject.getValue(y + 1));
                    
                    //here I'll try to save time by caling method that check if the 2 word frequence has been calculated first or not ?
                   
//                    double value;
//                    value=calculate_fitness(word_i,word_iPlus1);
//                    if(value==-1){
                    //"your_filed" is a filed to search a term. when index  files,the created  field Content  */

                    /*
                     * TermDocs reads directly from the ".frq" file in an index
                     * segment, where each term frequency is listed in document
                     * order. If slow make optimized index to merge multiple
                     * segments into a single segment. 
                    * //
                     */
                    ArrayList listaDocNums = new ArrayList();
                    ArrayList listaDocNums2 = new ArrayList();

                    ArrayList listaDocfreq = new ArrayList();
                    ArrayList listaDocfreq2 = new ArrayList();

                    ir.close();
                    d = FSDirectory.open(indexDir);
                    ir = IndexReader.open(d);
                    TermDocs termDocs = ir.termDocs(new Term("content", word_i));

                    while (termDocs.next()) {

                        listaDocfreq.add(1);
                        listaDocNums.add(termDocs.doc());

                    }
                    ir.close();
                    d = FSDirectory.open(indexDir);
                    ir = IndexReader.open(d);
                    TermDocs termDocs2 = ir.termDocs(new Term("content", word_iPlus1));

                    while (termDocs2.next()) {


                        listaDocfreq2.add(1);
                        listaDocNums2.add(termDocs2.doc());

                    }

                    final_count_freq = 0;
                    tmp = 0.0;

                    for (int k = 0; k < listaDocNums.size(); k++) {
                        int freq2 = lookForDocnumber(listaDocNums2, listaDocfreq2, (int) listaDocNums.get(k));
                        if (freq2 != 0) {

                            //it means simply add 1

                            final_count_freq = final_count_freq + 1;
                        }//end if
                    }

                    if (final_count_freq != 0) {

                        tmp = idf(ir.numDocs(), final_count_freq);
                        //recorde the value of tmp in file for word_i and word_iplus1
//                        fitness_file = new FileWriter(new File("C:\\fitness\\fitness_ga.txt"));
//                        PrintWriter print_line=new PrintWriter(fitness_file);
//                        print_line.println(word_i+" -"+word_iPlus1+" -"+tmp);
//                        print_line.close();
//                        fitness_file.close();
      //00000000000000000000000000000000000000000000000000000000000000000000000000000000000000                  
//                        BufferedWriter  output;
//output = new BufferedWriter(new FileWriter(new File("C:\\fitness\\fitness_ga.txt"),true));
//output.newLine();
//output.append(word_i+" -"+word_iPlus1+" -"+tmp);
//output.close();

                        w = w * tmp;
                        w_val.add(w);
                        coun++;
                    }//enf if !=0
                   
//                    }//end if value==-1
//                    else{
//                        w = w * value;
//                     w_val.add(value);   
//                    }
                }//end for y 

            }//end for close "chromosome size " x

        }//end of try  
        catch (CorruptIndexException ex) {
            Logger.getLogger(GA_Fitness.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GA_Fitness.class.getName()).log(Level.SEVERE, null, ex);
        }


        if (!w_val.isEmpty()) {
ABC_ParaphrasedQuery.add(query);
ABC_fitness.add(w);
            individual.setScore(w);
System.out.print(w);
        } else {
            individual.setScore(0);//return 0;
            System.out.print(0);
        }
        
         }//before starting search in previous PQ
        else{
             individual.setScore( found_previous_fit);
             System.out.print(found_previous_fit);
        }
    }//end of eval function 

    // for ABC algorithm
    public static boolean equal_arr(int[] list1, int[] list2) {

	  
	  
	  // Now test if every element is the same
	  for (int i = 0; i < list1.length; i++) {
		  if (list1[i] != list2[i])
			  return false; // If one is wrong then they all are wrong.
	  }
	  
	  // If all these tests worked, then they are identical.
	  return true;
}

    public double search_Previous_fitness(int[] arr)
    {
        double d=-1;
        for(int i=0;i<ParaphrasedQuery.size();i++)
        {
            if(equal_arr(arr,(int[])(ParaphrasedQuery.get(i))))
            {
             return (double)fitness.get(i) ;  
            }
        }
        return d;
    }
    public double evaluateABC(int[] a_subject) {

        double fund_previous_fit=-1;
        fund_previous_fit=search_Previous_fitness(a_subject);
        if( fund_previous_fit== -1)
        {
        double w = 1;
        int coun = 0;
        ArrayList w_val = new ArrayList();

        IndexReader ir;
        //File indexDir = new File("C:\\luceneIndex_Shingle");
        File indexDir = new File("C:\\luceneIndex_arb");



        try {
            d = FSDirectory.open(indexDir);
            ir = IndexReader.open(d);
            Double tmp = 0.0;
            int final_count_freq = 0;
            // start applying joint probability ( frequence )on the chromosome genes

            // System.out.println("))))))))) new eval ((((((((((((((((");  
            int gene_i = 0;
            // Returns the size of this Chromosome (the number of genes it contains).





            // int chromosome_Length=  a_subject.size();
            int chromosome_Length = a_subject.length;
            for (int x = 0; x < chromosome_Length && (int) a_subject[x] != 0; x++) {
                gene_i++;
                // String word_i= S.lookUp_syn((int)a_subject.getGene(x).getAllele());
                String word_i = S.lookUp_syn((int) a_subject[x]);
                //  w=1;
                for (int y = x; y < chromosome_Length - gene_i; y++) {

                    String word_iPlus1 = S.lookUp_syn((int) a_subject[y + 1]);
                    //"your_filed" is a filed to search a term. when index  files,the created  field Content  */

double value;
//                    value=calculate_fitness(word_i,word_iPlus1);
value=-1;
                    if(value==-1){

                    /*
                     * TermDocs reads directly from the ".frq" file in an index
                     * segment, where each term frequency is listed in document
                     * order.
                     *
                     * If slow make optimized index to merge multiple segments
                     * into a single segment. 
//
                     */
                    ArrayList listaDocNums = new ArrayList();
                    ArrayList listaDocNums2 = new ArrayList();

                    ArrayList listaDocfreq = new ArrayList();
                    ArrayList listaDocfreq2 = new ArrayList();

                    ir.close();
                    d = FSDirectory.open(indexDir);
                    ir = IndexReader.open(d);
                    TermDocs termDocs = ir.termDocs(new Term("content", word_i));

                    while (termDocs.next()) {

                        listaDocfreq.add(1);
                        listaDocNums.add(termDocs.doc());
                        //  System.out.println("*********************termdoc id "+termDocs.doc()+"termdoc frq"+termDocs.freq()); 
                        // System.out.println(listaDocNums.get(o));
                        //o++;
                    }
                    ir.close();
                    d = FSDirectory.open(indexDir);
                    ir = IndexReader.open(d);
                    TermDocs termDocs2 = ir.termDocs(new Term("content", word_iPlus1));

                    while (termDocs2.next()) {

                        // listaDocfreq2.add(termDocs2.freq());
                        listaDocfreq2.add(1);
                        listaDocNums2.add(termDocs2.doc());
                        // System.out.println("termdoc2 id "+termDocs2.doc()+"termdoc2 frq"+termDocs2.freq());
                    }


                    final_count_freq = 0;
                    tmp = 0.0;

                    for (int k = 0; k < listaDocNums.size(); k++) {
                        int freq2 = lookForDocnumber(listaDocNums2, listaDocfreq2, (int) listaDocNums.get(k));
                        if (freq2 != 0) {

                            //it means simply add 1
                            // final_count_freq=final_count_freq+ (int) listaDocfreq.get(k); 
                            final_count_freq = final_count_freq + 1;
                        }//end if
                    }
                    // System.out.println("final_count_freq "+final_count_freq);
                    if (final_count_freq != 0) { //  w =w+final_count_freq;

                        tmp = idf(ir.numDocs(), final_count_freq);
                        
//                         BufferedWriter  output;
//output = new BufferedWriter(new FileWriter(new File("C:\\fitness\\fitness_ga.txt"),true));
//output.newLine();
//output.append(word_i+" -"+word_iPlus1+" -"+tmp);
//output.close();

                        w = w * tmp;
                        w_val.add(w);
                        coun++;
                    }//enf if !=0
                    
                     }//end if value==-1
                    else{
                        w = w * value;
                     w_val.add(value);   
                    }
                    // System.out.println("w  "+w +"counter "+ coun);

                }//end for y 

//      if (final_count_freq !=0)
//      { //  w =w+final_count_freq;
//          
//           tmp = idf( ir.numDocs(),final_count_freq);
//             w=w*tmp;
//             w_val.add(w);
//             coun++;
//      }//enf if !=0
//    System.out.println("w  "+w +"counter "+ coun);              

            }//end for close "chromosome size " x
            //I add extra close    
            ir.close();



        }//end of try  
        catch (CorruptIndexException ex) {
            Logger.getLogger(GA_Fitness.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GA_Fitness.class.getName()).log(Level.SEVERE, null, ex);
        }


        if (!w_val.isEmpty()) {//
            //System.out.print("final W ="+w);
            
            ParaphrasedQuery.add(a_subject);
            fitness.add(w);
            return w;


        } else {
            return 0;
        }

    }//before starting search in previous PQ
        else{
            return fund_previous_fit;
        }
    }//end of eval function ABC

    public static int lookForDocnumber(ArrayList docnumlist, ArrayList docfreqlist, int doc_number) {
        int found = 0;
        for (int t = 0; t < docnumlist.size(); t++) {
            if ((int) docnumlist.get(t) == doc_number) // found=(int)docfreqlist.get(t);
            {
                found = 1;
            }
        }
        //return the found frequinse for the docID
        return found;
    }

    private static double idf(double d, int dt) {

        //return  Math.log(d/dt)/Math.log(10);
        //I need the frequence of word1+word2 /all doc
        return dt / d;
    }
    
}
