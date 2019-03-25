package com.pruebas.metodosrecursive;

public class Metodos {
	
	public static int factorial(int x)
	 {
	    if (x > -1 && x < 2) {
	    	return 1;  // Cuando -1 < x < 2 devolvemos 1 puesto que 0! = 1 y 1! = 1
	    }
	    //else if (x < 0) return 0;       // Error no existe factorial de números negativos
	    return x * factorial(x - 1);    // Si x >= 2 devolvemos el producto de x por el factorial de x - 1
	 }
	
	public static int sum(int n) {
		if (n <= 0) {
			return 0;
		}
		return n + sum(n-1);
	}
	
	public static int metodo(int valor){
		if (valor<4) {
			return valor;
		}
		return metodo(valor-1)*metodo(valor-2);		
	}
	
	
	public static void main(String[] args) {
		int numero = 5;
		
		//System.out.println("Factorial de "+numero+ " es: " + metodo(5));
		System.out.println("Respuesta de  "+numero+ " es: " + metodo(numero));
		//Juan =  
		//Camila = 
		//System.out.println("La suma desde "+numero+ " es: " + sum(numero));

	}

}
