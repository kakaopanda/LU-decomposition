package project;

import java.util.Scanner;

public class project
{
    public static void main(String args[])
    {
      Scanner s = new Scanner(System.in);
      int m, n;
      while(true) // �ùٸ� ������ ����� ũ�⸦ �Է� ���� �� ���� �ݺ� ����.
      {
        System.out.print("LU���ظ� ������ ����� ũ��(��*��)�� �Է� ���ּ���: ");
        m = s.nextInt();
        n = s.nextInt();
        if((n<1 || n>10)||(m<1 || m>10)){
        	System.out.println("��Ȯ�� ������ �Է� ���ּ���.");
        	continue;
        }
        else
        	break;
    }
    
        double[][] matrix = new double[m][n]; // �Է� ���� ����� ������ ���� (m*n)����� ǥ���ϴ� �迭����. 
        
        System.out.println("����� ���Ҹ� �Է����ּ���.");
        for(int i=0; i<m; i++) // �Է¹��� ���ҵ��� ����� ���ҷ� ����.
        for(int j=0; j<n; j++)
            matrix[i][j] = s.nextDouble();
        
        double[][] lower = new double[m][m];
        double[][] upper = new double[m][n];
        System.out.println();
        System.out.println("- ���A -");
        print_Array(matrix);
        
        System.out.println("<LU ���ظ� ������ ����Դϴ�.>");
        array_Element(upper,lower,matrix);
        
        System.out.println("- �ϻﰢ���L -");
        print_Array(lower);
        System.out.println("- ��ﰢ���U -");
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
		lower_Array_first(l); // �ϻﰢ��� L�� ���� �⺻��� ����.
		upper_Array_first(u); // ��ﰢ��� U�� ���� �⺻��� ����.
    	    
		int m = 0 ,n = 0; // ����� ���Ҹ� �����ϴ� ����ܰ踦 �ǹ�.
		if(m==0 && n==0) // m = 0, n = 0 ����.
		{
			for(int i=0; i<a[0].length; i++) // u(1,1) ~ u(1,n)�� ���Ҵ���.
				u[0][i] = a[0][i];
				m++;
		}
    	    
		while(true){
			if(n==a[0].length)
				break;
    	   
			for(int i=m; i<a.length; i++)
				l[i][n] = relation_lower(i+1,n+1,u,l,a); // l(2,1) ~ l(m,1)�� ���Ҵ���.
			
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
