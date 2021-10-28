package project;

import java.util.Scanner;

public class project
{
    public static void main(String args[])
    {
      Scanner s = new Scanner(System.in);
      int m, n;
      while(true) // 올바른 범위의 행렬의 크기를 입력 받을 때 까지 반복 진행.
      {
        System.out.print("LU분해를 진행할 행렬의 크기(행*열)를 입력 해주세요: ");
        m = s.nextInt();
        n = s.nextInt();
        if((n<1 || n>10)||(m<1 || m>10)){
        	System.out.println("정확한 범위를 입력 해주세요.");
        	continue;
        }
        else
        	break;
    }
    
        double[][] matrix = new double[m][n]; // 입력 받은 행렬의 차원을 통해 (m*n)행렬을 표현하는 배열생성. 
        
        System.out.println("행렬의 원소를 입력해주세요.");
        for(int i=0; i<m; i++) // 입력받은 원소들을 행렬의 원소로 저장.
        for(int j=0; j<n; j++)
            matrix[i][j] = s.nextDouble();
        
        double[][] lower = new double[m][m];
        double[][] upper = new double[m][n];
        System.out.println();
        System.out.println("- 행렬A -");
        print_Array(matrix);
        
        System.out.println("<LU 분해를 진행한 결과입니다.>");
        array_Element(upper,lower,matrix);
        
        System.out.println("- 하삼각행렬L -");
        print_Array(lower);
        System.out.println("- 상삼각행렬U -");
        print_Array(upper);
    }
    
    public static double sigma_lower(int i, int j, double[][] u, double[][] l, double[][] a) {
        double sum = 0;
        for(int k=0; k<=(j-1); k++)
        	sum += (u[k][j]*l[i][k]);
        return sum;
}

    public static double sigma_upper(int i, int j, double[][] u, double[][] l, double[][] a) {
        double sum = 0;
        for(int k=0; k<=(i-1); k++)
        	sum += (u[k][j]*l[i][k]);
        return sum;
}
    
    public static double relation_lower(int i, int j, double[][] u, double[][] l, double[][] a) {
    	return (a[i-1][j-1] - sigma_lower(i-1, j-1, u, l, a))/u[j-1][j-1];}
    
    public static double relation_upper(int i, int j, double[][] u, double[][] l, double[][] a) {
    	return (a[i-1][j-1] - sigma_upper(i-1, j-1, u, l, a));}

    public static void lower_Array_first(double[][] array) {
    	for(int i=0; i<array.length; i++)
    		for(int j=i; j<array[i].length; j++){
    			if(i==j)
    				array[i][j] = 1;
    			else
    				array[i][j] = 0;
    		}
	}
    	    
	public static void upper_Array_first(double[][] array) {
		for(int i=1; i<array.length; i++)
			for(int j=0; j<i; j++)
				array[i][j] = 0;
	}
    	    
	public static void array_Element(double[][] u, double[][] l, double[][] a) {
		lower_Array_first(l); // 하삼각행렬 L에 대한 기본요소 대입.
		upper_Array_first(u); // 상삼각행렬 U에 대한 기본요소 대입.
    	    
		int m = 0 ,n = 0; // 행렬의 원소를 대입하는 진행단계를 의미.
		if(m==0 && n==0) // m = 0, n = 0 진입.
		{
			for(int i=0; i<a[0].length; i++) // u(1,1) ~ u(1,n)의 원소대입.
				u[0][i] = a[0][i];
				m++;
		}
    	    
		while(true){
			if(n==a[0].length)
				break;
    	   
			for(int i=m; i<a.length; i++)
				l[i][n] = relation_lower(i+1,n+1,u,l,a); // l(2,1) ~ l(m,1)의 원소대입.
			
			n++;

			if(n==a[0].length || m==a.length)
				break;

			for(int i=n; i<a[0].length; i++)
				u[m][i] = relation_upper(m+1,i+1,u,l,a);
			
			m++;
		}
	}

	public static void print_Array(double [][] array) {
		for(int i=0; i<array.length; i++){
			for(int j=0; j<array[i].length; j++)
				System.out.printf("%5.1f",array[i][j]);
			System.out.println();
		}
		System.out.println();
		}
	}
