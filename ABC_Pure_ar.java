/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IR_Paraphrased;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import net.didion.jwnl.JWNLException;

/**
 *
 * @author Abeer
 */
public class ABC_Pure_ar {
    
/* Control Parameters of ABC algorithm*/
	int NP=100; /* The number of colony size (employed bees+onlooker bees)*/
	int FoodNumber = NP/2; /*The number of food sources equals the half of the colony size*/
	//int limit = 100;  /*A food source which could not be improved through "limit" trials is abandoned by its employed bee*/
		int limit = 100;  /*A food source which could not be improved through "limit" trials is abandoned by its employed bee*/

        //int maxCycle = 2500; /*The number of cycles for foraging {a stopping criteria}*/
	int maxCycle = 50; /*The number of cycles for foraging {a stopping criteria}*/

	/* Problem specific variables*/
	static int D; /*       in my propleam the d= query size  I think no it's not                                     */
//	double lb = -5.12; /*lower bound of the parameters. */
//	double ub = 5.12; /*upper bound of the parameters. lb and ub can be defined as arrays for the problems of which parameters have different bounds*/

         int lb[] ;//= new int[D]; /*lower bound of the parameters. */
	int ub[] ;//= new int[D]; /*upper bound of the parameters. 
        //lb and ub can be defined as arrays for the problems of which parameters have different bounds*/



	//int runtime = 30;  /*Algorithm can be run many times in order to see its robustness*/
int runtime = 1;  /*Algorithm can be run many times in order to see its robustness*/
	int dizi1[]=new int[10];
        ArrayList t=new ArrayList();
        //Individual<ObjectChromosome> tryThis [][] =new Individual<ObjectChromosome> [FoodNumber] [D];
        Object ty[][]=new Object [FoodNumber][D];//becuse I want each D to be an array (Query)
	int Foods[][]=new int[FoodNumber][D];        /*Foods is the population of food sources. Each row of Foods matrix is a vector holding D parameters to be optimized. The number of rows of Foods matrix equals to the FoodNumber*/
	double f[]=new double[FoodNumber];        /*f is a vector holding objective function values associated with food sources */
	double fitness[]=new double[FoodNumber];      /*fitness is a vector holding fitness (quality) values associated with food sources*/
	double trial[]=new double[FoodNumber];         /*trial is a vector holdin g trial numbers through which solutions can not be improved*/
	double prob[]=new double[FoodNumber];          /*prob is a vector holding probabilities of food sources (solutions) to be chosen*/
	 int solution[]=new int[D];            /*New solution (neighbour) produced by v_{ij}=x_{ij}+\phi_{ij}*(x_{kj}-x_{ij}) j is a randomly chosen parameter and k is a randomlu chosen solution different from i*/
	private  ArrayList PQ=new ArrayList();
        private  ArrayList previous_fitness=new ArrayList();
        
                   
	double ObjValSol;              /*Objective function value of new solution*/
	double FitnessSol;              /*Fitness value of new solution*/
	int neighbour, param2change;                   /*param2change corrresponds to j, neighbour corresponds to k in equation v_{ij}=x_{ij}+\phi_{ij}*(x_{kj}-x_{ij})*/

	double GlobalMin;                       /*Optimum solution obtained by ABC algorithm*/
	
        double GlobalParams[]=new double[D];                   /*Parameters of the optimum solution*/
	double GlobalMins[]=new double[runtime];            
	         /*GlobalMins holds the GlobalMin of each run in multiple runs*/
	double r; /*a random number in the range [0,1)*/

	/*a function pointer returning double and taking a D-dimensional array as argument */
	/*If your function takes additional arguments then change function pointer definition and lines calling "...=function(solution);" in the code*/


	//typedef double (*FunctionCallback)(double sol[D]);  

	/*benchmark functions */
 
//	double sphere(double sol[D]);
//	double Rosenbrock(double sol[D]);
//	double Griewank(double sol[D]);
//	double Rastrigin(double sol[D]);

	/*Write your own objective function name instead of sphere*/
//	FunctionCallback function = &sphere;
// to calculate the objective function for IR system I used this parameter
      private static GA_Fitness_ar OB_fun =new GA_Fitness_ar();
        public static ArrayList syn1=new ArrayList();
    public static ArrayList syn2=new ArrayList();
    public static ArrayList syn3=new ArrayList();
    public static ArrayList syn4=new ArrayList();
//        private Population<ObjectChromosome> Population = null;
	/*Fitness function*/
        
        //when we combine the ABC with GA we will define foodnumber as population size from GA
	public void setfoodNumber(int population_size){
         FoodNumber=population_size;
       //  System.out.println("FoodNumber=population_size;"+ FoodNumber);
         NP=2*FoodNumber;
            
        }
        public void set_syn4(ArrayList l){
            syn4.clear();
  syn4.addAll(l);   
 }  
  public void set_syn3(ArrayList l){
      syn3.clear();
  syn3.addAll(l);   
 }  
   public void set_syn2(ArrayList l){
       syn2.clear();
  syn2.addAll(l);   
 }  
   public void set_syn1(ArrayList l){
       syn1.clear();
  syn1.addAll(l);   
 }  
       public void setD(Thesaurus_arab_AWN g,int QueryLength) throws JWNLException, FileNotFoundException, IOException{
       //  D=QueryLength-1;
         D=QueryLength;
       //  System.out.println("D "+D);
           OB_fun.Set_thesaurus(g,syn1, syn2, syn3, syn4); 
           OB_fun.set_query_length(D);
        Foods=new int[FoodNumber][D]; 
        f=new double[FoodNumber];        /*f is a vector holding objective function values associated with food sources */
fitness=new double[FoodNumber];      /*fitness is a vector holding fitness (quality) values associated with food sources*/
	trial=new double[FoodNumber];         /*trial is a vector holdin g trial numbers through which solutions can not be improved*/
	prob=new double[FoodNumber];          /*prob is a vector holding probabilities of food sources (solutions) to be chosen*/
	 solution=new int[D];      
         GlobalParams=new double[D]; 
       } 
        public void setLB_UB(int[] l,int[]u){
            //arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
//Copies an array from the specified source array, beginning at the specified position, to the specified position of the destination array.
               System.out.println("l legth  = "+l.length);
               
               lb = new int[D]; 
	 ub = new int[D];
         
         System.arraycopy(l,0,lb,0,l.length);
         System.arraycopy(u,0,ub,0,u.length);
         
                 } 
        double CalculateFitness(double fun) 
	 {
//             OB_fun.Set_thesaurus(syn1,syn2, syn3, syn4);
//return OB_fun.evaluateABC(sol);
             return fun;

//		 double result=0;
//		 if(fun>=0)
//		 {
//			 result=1/(fun+1);
//		 }
//		 else
//		 {
//			 
//			 result=1+Math.abs(fun);
//		 }
//		 return result;
	 }

	/*The best food source is memorized*/
	public void MemorizeBestSource() 
	{
	   int i,j;
	    
		for(i=0;i<FoodNumber;i++)
		{
                   // System.out.println(f[i]+" comp   "+GlobalMin); 
		if (f[i]>GlobalMin && ! notintialquery(i))
			{
	        GlobalMin=f[i];
               // System.out.println(f[i]+">"+GlobalMin); 
	        for(j=0;j<D;j++)
	           GlobalParams[j]=Foods[i][j];
	        }
               
		}
	 }
public boolean notintialquery(int i)
{
boolean is_initial=false;
int [] ar=new int[D];
for(int j=0;j<D;j++)
	           ar[j]=Foods[i][j];

if(equal_arr(ar,lb))
     is_initial=true;

return is_initial;

}
	/*Variables are initialized in the range [lb,ub]. If each parameter has different range, use arrays lb[j], ub[j] instead of lb and ub */
	/* Counters of food sources are also initialized in this function*/


	void init(int index) throws JWNLException, FileNotFoundException, IOException
	{
	   int j;
	   for (j=0;j<D;j++)
			{
	        r = (   (double)Math.random()*32767 / ((double)32767+(double)(1)) );
	        Foods[index][j]=((int)( r*(ub[j]-lb[j])))+lb[j];// I need to change this with population from GA
			solution[j]=Foods[index][j];
			}
          
            int[] s=new int[D];
            System.arraycopy(solution, 0, s, 0, D);
               f[index]=calculateFunction(s);
		fitness[index]=CalculateFitness(f[index]);
		trial[index]=0;
	}

	/*All food sources are initialized */
	public void initial() throws JWNLException, FileNotFoundException, IOException 
	{
		int i;
                
		for(i=0;i<FoodNumber;i++)
		{
           
                init(i);
          
          
                }        
				                
		GlobalMin=f[0];
	    for(i=0;i<D;i++)
	    GlobalParams[i]=Foods[0][i];


	}

	public void SendEmployedBees() throws JWNLException, FileNotFoundException, IOException
	{
	  int i,j;
	  /*Employed Bee Phase*/
	   for (i=0;i<FoodNumber;i++)
	        {
	        /*The parameter to be changed is determined randomly*/
	        r = ((double) Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        param2change=(int)(r*(D));
	        
	        /*A randomly chosen solution is used in producing a mutant solution of the solution i*/
	        r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        neighbour=(int)(r*FoodNumber);

	        /*Randomly selected solution must be different from the solution i*/        
	       // while(neighbour==i)
	       // {
	       // r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	       // neighbour=(int)(r*FoodNumber);
	       // }
	        for(j=0;j<D;j++)
	        solution[j]=Foods[i][j];

	        /*v_{ij}=x_{ij}+\phi_{ij}*(x_{kj}-x_{ij}) */
	        r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        solution[param2change]=(int)(Foods[i][param2change]+(Foods[i][param2change]-Foods[neighbour][param2change])*(r-0.5)*2);

	        /*if generated parameter value is out of boundaries, it is shifted onto the boundaries*/
              //  System.out.println("j  = "+j);
	        if (solution[param2change]<lb[param2change])
	           solution[param2change]=lb[param2change];
	        if (solution[param2change]>ub[param2change])
	           solution[param2change]=ub[param2change];
	        ObjValSol=calculateFunction(solution);
	        FitnessSol=CalculateFitness(ObjValSol);
	        
	        /*a greedy selection is applied between the current solution i and its mutant*/
	        if (FitnessSol>fitness[i])
	        {
	        
	        /*If the mutant solution is better than the current solution i, replace the solution with the mutant and reset the trial counter of solution i*/
	        trial[i]=0;
	        for(j=0;j<D;j++)
	        Foods[i][j]=solution[j];
	        f[i]=ObjValSol;
	        fitness[i]=FitnessSol;
	        }
	        else
	        {   /*if the solution i can not be improved, increase its trial counter*/
	            trial[i]=trial[i]+1;
	        }


	        }

	        /*end of employed bee phase*/

	}

	/* A food source is chosen with the probability which is proportioal to its quality*/
	/*Different schemes can be used to calculate the probability values*/
	/*For example prob(i)=fitness(i)/sum(fitness)*/
	/*or in a way used in the metot below prob(i)=a*fitness(i)/max(fitness)+b*/
	/*probability values are calculated by using fitness values and normalized by dividing maximum fitness value*/
	public void CalculateProbabilities()
	{
	     int i;
	     double maxfit;
	     maxfit=fitness[0];
	  for (i=1;i<FoodNumber;i++)
	        {
	           if (fitness[i]>maxfit)
	           maxfit=fitness[i];
	        }

	 for (i=0;i<FoodNumber;i++)
	        {
	         prob[i]=(0.9*(fitness[i]/maxfit))+0.1;
	        }

	}

	public void SendOnlookerBees() throws JWNLException, FileNotFoundException, IOException
	{

	  int i,j,t;
	  i=0;
	  t=0;
	  /*onlooker Bee Phase*/
	  while(t<FoodNumber)
	        {

	        r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        if(r<prob[i]) /*choose a food source depending on its probability to be chosen*/
	        {        
	        t++;
	        
	        /*The parameter to be changed is determined randomly*/
	        r = ((double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        param2change=(int)(r*(D));
	        
	        /*A randomly chosen solution is used in producing a mutant solution of the solution i*/
	        r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        neighbour=(int)(r*FoodNumber);

	        /*Randomly selected solution must be different from the solution i*/        
	        while(neighbour == i)
	        {
	        	//System.out.println(Math.random()*32767+"  "+32767);
	        r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        neighbour=(int)(r*FoodNumber);
	        }
	        for(j=0;j<D;j++)
	        solution[j]=Foods[i][j];

	        /*v_{ij}=x_{ij}+\phi_{ij}*(x_{kj}-x_{ij}) */
	        r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        solution[param2change]=(int) (Foods[i][param2change]+(Foods[i][param2change]-Foods[neighbour][param2change])*(r-0.5)*2);

	        /*if generated parameter value is out of boundaries, it is shifted onto the boundaries*/
	        if (solution[param2change]<lb[param2change])
	           solution[param2change]=lb[param2change];
	        if (solution[param2change]>ub[param2change])
	           solution[param2change]=ub[param2change];
	        ObjValSol=calculateFunction(solution);
	        FitnessSol=CalculateFitness(ObjValSol);
	        
	        /*a greedy selection is applied between the current solution i and its mutant*/
	        if (FitnessSol>fitness[i])
	        {
	        /*If the mutant solution is better than the current solution i, replace the solution with the mutant and reset the trial counter of solution i*/
	        trial[i]=0;
	        for(j=0;j<D;j++)
	        Foods[i][j]=solution[j];
	        f[i]=ObjValSol;
	        fitness[i]=FitnessSol;
	        }
	        else
	        {   /*if the solution i can not be improved, increase its trial counter*/
	            trial[i]=trial[i]+1;
	        }
	        } /*if */
	        i++;
	        if (i==FoodNumber)
	        i=0;
	        }/*while*/

	        /*end of onlooker bee phase     */
	}

	/*determine the food sources whose trial counter exceeds the "limit" value. In Basic ABC, only one scout is allowed to occur in each cycle*/
	public void SendScoutBees() throws JWNLException, FileNotFoundException, IOException
	{
	int maxtrialindex,i;
	maxtrialindex=0;
	for (i=1;i<FoodNumber;i++)
	        {
	         if (trial[i]>trial[maxtrialindex])
	         maxtrialindex=i;
	        }
	if(trial[maxtrialindex]>=limit)
	{
		init(maxtrialindex);
	}
        
        //send the new population back 
        
	}




//another posable return method


 double calculateFunction( int[] sol) throws JWNLException, FileNotFoundException, IOException
{

//OB_fun.Set_thesaurus(syn1,syn2, syn3, syn4);
    double r;
    int[] array=new int[D];
    System.arraycopy(sol,0, array, 0, D);
    r=search_Previous_fitness(array);
 if(r==-1)  {
    
     
     PQ.add(array);
 //print_list(PQ);
r=OB_fun.evaluateABC(sol);

//System.out.println("fitness  "+ r );
 previous_fitness.add(r);
 }
 //System.out.println(" "+ sol[0]+", "+sol[1]+" fitness = "+r);

    for(int j=0;j<D;j++)
		{
			//System.out.println("GlobalParam[%d]: %f\n",j+1,GlobalParams[j]);
			System.out.print(" "+ sol[j]+", ");
		}
		  System.out.println(" fitness = "+r);
                    
 
 return  r;
}

  public double search_Previous_fitness(int[] arr)
    {
        double d=-1;
        int[] array2=new int[D];
        System.arraycopy(arr,0, array2, 0, D);
        for(int i=0;i<PQ.size();i++)
        {
            int[] array3=new int[D];
            System.arraycopy((int[])(PQ.get(i)),0, array3, 0, D);
            if(equal_arr(array2,array3))
            {
             return (double)previous_fitness.get(i) ;  
            }
        }
        return d;
    }
 
public static boolean equal_arr(int[] list1, int[] list2) {

	  
	  
	  // Now test if every element is the same
	  for (int i = 0; i < list1.length; i++) {
		  if (list1[i] != list2[i])
			  return false; // If one is wrong then they all are wrong.
	  }
	  
	  // If all these tests worked, then they are identical.
	  return true;
}
private double find_fitness(int soul[])
{
    double result= -1;
    int[] array4=new int[D];
    System.arraycopy(soul,0, array4, 0, D);
    for(int rowind=0;rowind<Foods.length;rowind++)
    {
       int [] row = new int[D];
      System.arraycopy(Foods[rowind],0, row, 0, D); 
       
       if (row != null) {
          
             if (equal_arr(array4,row)) {
                 result=fitness[rowind];
                 System.out.print("bingo  :)");
                 return result;
             }
          
       }
    

 
    }
    
    
    return result;
}



double sphere(int sol[])
	{
	int j;
	double top=0;
	for(j=0;j<D;j++)
	{
	top=top+sol[j]*sol[j];
	}
	return top;
	}

	double Rosenbrock(int sol[])
	{
	int j;
	double top=0;
	for(j=0;j<D-1;j++)
	{
	top=top+100*Math.pow((sol[j+1]-Math.pow((sol[j]),(double)2)),(double)2)+Math.pow((sol[j]-1),(double)2);
	}
	return top;
	}

	 double Griewank(int sol[])
	 {
		 int j;
		 double top1,top2,top;
		 top=0;
		 top1=0;
		 top2=1;
		 for(j=0;j<D;j++)
		 {
			 top1=top1+Math.pow((sol[j]),(double)2);
			 top2=top2*Math.cos((((sol[j])/Math.sqrt((double)(j+1)))*Math.PI)/180);

		 }	
		 top=(1/(double)4000)*top1-top2+1;
		 return top;
	 }

	 double Rastrigin(int sol[])
	 {
		 int j;
		 double top=0;

		 for(j=0;j<D;j++)
		 {
			 top=top+(Math.pow(sol[j],(double)2)-10*Math.cos(2*Math.PI*sol[j])+10);
		 }
		 return top;
	 }
     public static void print_list(ArrayList list1)
   {
       for(int y=0;y<list1.size();y++)
{
    for (int i=0;i<D;i++){
        int[] f=new int[D];
        f= (int [])list1.get(y);
       // System.out.print(f[i] + " , ");
    }
//System.out.println();
}
   }
}

