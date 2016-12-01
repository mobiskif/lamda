package neuron;

import java.util.HashMap;
import java.util.Map;

public class NeuronModel {
	int numNeurons=7;
	int numInputs=4;
	
	Map<Object, Object> inp = new HashMap<Object, Object>();
	int[][] link = new int[numNeurons][numInputs]; //[нейрон] [вход] связан с выходом нейрона [значение]
	int[][] W = new int[numNeurons][numInputs]; //[нейрон] [вход] имеет вес [значение]
	int[][] X = new int[numNeurons][numInputs]; //[нейрон] [вход] имеет вход [значение]
	int[] S = new int[numNeurons]; //[нейрон] [вход] имеет выход [значение]

	public NeuronModel() {
		link = link();
	}
	
	int[] S() {
		S = new int[numNeurons];
		return S;
	}
	
	int[][] X() {
		X = new int[numNeurons][numInputs];
		return X;
	}
	
	int[][] W() {
		W = new int[numNeurons][numInputs];
		return W;
	}
	
	int[][] link() {
		link = new int[numNeurons][numInputs];
		link[0][0] = 0;
		link[0][1] = 0;
		link[0][2] = 0;
		link[0][3] = 0;
		
		link[1][0] = 1;
		link[1][1] = 1;
		link[1][2] = 1;
		link[1][3] = 1;

		link[2][0] = 2;
		link[2][1] = 2;
		link[2][2] = 2;
		link[2][3] = 2;

		link[3][0] = 3;
		link[3][1] = 3;
		link[3][2] = 3;
		link[3][3] = 3;

		link[4][0] = 0;
		link[4][1] = 1;
		link[4][2] = 2;
		link[4][3] = 3;

		link[5][0] = 0;
		link[5][1] = 1;
		link[5][2] = 2;
		link[5][3] = 3;

		link[6][0] = 4;
		link[6][1] = 4;
		link[6][2] = 5;
		link[6][3] = 5;
		return link;
	}
	
	
	void transferForward( int[] s, int[][] x) {
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				int sourceID = link[i][j];
				x[i][j] = s[sourceID];
			}
		}
	}
	
	void printX() {
		X=X();
		for (int[] a : X) {
			for (int b : a) {
				System.out.print(b+" ");
			}
			System.out.println("<=");
		}	
	}
	
	void printS() {
		S=S();
		for (int a : S) {
			System.out.println("S="+a);
		}	

	}

}
