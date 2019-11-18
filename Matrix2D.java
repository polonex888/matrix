
package matrix;

public class Matrix2D {
	
	private double[][] matrix;
	
	public Matrix2D(double[] vector) {
		this.matrix = new double[vector.length][1];
		for(int i=0; i<vector.length; i++) {
			this.matrix[i][0] = vector[i];
		}
	}
	
	public Matrix2D(double[][] vector) {
		this.matrix = new double[vector.length][vector[0].length];
		for(int r=0; r<vector.length; r++) {
			for(int c=0; c<vector[r].length; c++) {
				this.matrix[r][c] = vector[r][c];
			}
		}
	}
	
	public double[][] getArrays() {
		return this.matrix;
	}
	
	public int getRow() {
		return this.matrix.length;
	}
	
	public int getCol() {
		return this.matrix[0].length;
	}
	
	
	public Matrix2D T() {
		double[][] t = new double[this.getCol()][this.getRow()];
		for(int r=0; r<t.length; r++){
			for(int c=0; c<t[r].length; c++){
				t[r][c] = this.matrix[c][r];
			}
		}
		return new Matrix2D(t);
	}


	private void changeValue(int row, int col, double a){
		this.matrix[row][col] = a;
	}

	private double getValue(int row, int col){
		return this.matrix[row][col];
	}

	
	public static Matrix2D dot(Matrix2D a, Matrix2D b){
		double[][] tmp = new double[a.getRow()][b.getCol()];
		for(int r=0; r<a.getRow(); r++){
			for(int c=0; c<b.getCol(); c++){
				double sum = 0;
				for(int k=0; k<b.getRow(); k++){
					sum += a.getValue(r, k) * b.getValue(k, c);
				}
				tmp[r][c] = sum;
			}
		}
		return new Matrix2D(tmp);
	}
	
	
	public static Matrix2D add(Matrix2D a, Matrix2D b){
		double[][] tmp = new double[a.getRow()][a.getCol()];
		for(int c=0; c<a.getRow(); c++){
			for(int r=0; r<a.getCol(); r++){
				tmp[r][c] = a.getValue(r, c) + b.getValue(r, c);
			}
		}
		return new Matrix2D(tmp);
	}

	
	public static Matrix2D sub(Matrix2D a, Matrix2D b){
		double[][] tmp = new double[a.getRow()][a.getCol()];
		for(int c=0; c<a.getRow(); c++){
			for(int r=0; r<a.getCol(); r++){
				tmp[r][c] = a.getValue(r, c) - b.getValue(r, c);
			}
		}
		return new Matrix2D(tmp);
	}
	
	
	public static Matrix2D sqrt(Matrix2D a){
		double[][] tmp = new double[a.getRow()][a.getCol()];
		for(int r=0; r<a.getRow(); r++){
			for(int c=0; c<a.getCol(); c++){
				tmp[r][c] = Math.sqrt(a.getValue(r, c));
			}
		}
		return new Matrix2D(tmp);
	}

	
	public static Matrix2D pow(Matrix2D a, double b){
		double[][] tmp = new double[a.getRow()][a.getCol()];
		for(int r=0; r<a.getRow(); r++){
			for(int c=0; c<a.getCol(); c++){
				tmp[r][c] = Math.pow(a.getValue(r, c), b);
			}
		}
		return new Matrix2D(tmp);
	}
	
	public static Matrix2D prod(double a, Matrix2D b){
		double[][] tmp = new double[b.getRow()][b.getCol()];
		for(int r=0; r<b.getRow(); r++){
			for(int c=0; c<b.getCol(); c++){
				tmp[r][c] = b.getValue(r, c) * a;
			}
		}
		return new Matrix2D(tmp);
	}
	
	public static Matrix2D mult(Matrix2D a, Matrix2D b){
		if(a.getCol() == b.getRow()){
			double[][] tmp = new double[a.getRow()][b.getCol()];
			for(int r=0; r<a.getRow(); r++){
				for(int c=0; c<b.getCol(); c++){
					double sum = 0;
					for(int i=0; i<b.getRow(); i++){
						sum += a.getValue(r, i) * b.getValue(i, c);
					}
					tmp[r][c] = sum;
				}
			}
			return new Matrix2D(tmp);
		}
		else{
			return null;
		}
	}

	public static Matrix2D catHorizon(Matrix2D a, Matrix2D b){
		if(a.getRow() == b.getRow()){
			double[][] tmp = new double[a.getRow()][a.getCol() + b.getCol()];
			for(int r=0; r<a.getRow(); r++){
				for(int c=0; c<a.getCol(); c++){
					tmp[r][c] = a.getValue(r, c);
				}
				for(int c=0; c<b.getCol(); c++){
					tmp[r][a.getCol()+c] = b.getValue(r, c);
				}
			}
			return new Matrix2D(tmp);
		}
		return null;
	}
	
	public static Matrix2D catVertical(Matrix2D a, Matrix2D b){
		if(a.getCol() == b.getCol()){
			double[][] tmp = new double[a.getRow() + b.getRow()][a.getCol()];
			for(int c=0; c<a.getCol(); c++){
				for(int r=0; r<a.getRow(); r++){
					tmp[r][c] = a.getValue(r, c);
				}
				for(int r=0; r<b.getRow(); r++){
					tmp[a.getRow()+r][c] = b.getValue(r, c);
				}
			}
			return new Matrix2D(tmp);
		}
		else return null;
	}

	
	private static Matrix2D pivodRow(int i, int j, Matrix2D a){
		double[][] tmp = new double[a.getRow()][a.getCol()];
		for(int r=0; r<a.getRow(); r++){
			for(int c=0; c<a.getCol(); c++){
				if(r == i)tmp[r][c] = a.getValue(j, c);
				else if(r == j)tmp[r][c] = a.getValue(i, c);
				else tmp[r][c] = a.getValue(r, c);
			}
		}
		return new Matrix2D(tmp);
	}
	
	private static Matrix2D multRow(int i, double k, Matrix2D a) {
		double[][] tmp = new double[a.getRow()][a.getCol()];
		for(int r=0; r<a.getRow(); r++){
			for(int c=0; c<a.getCol(); c++){
				if(r == i)tmp[r][c] = a.getValue(r, c) * k;
				else tmp[r][c] = a.getValue(r, c);
			}
		}
		return new Matrix2D(tmp);	
	}
	
	private static Matrix2D addRow(int i, int j, Matrix2D a) {
		double[][] tmp = new double[a.getRow()][a.getCol()];
		for(int r=0; r<a.getRow(); r++){
			for(int c=0; c<a.getCol(); c++){
				if(r == i)tmp[r][c] = a.getValue(r, c) + a.getValue(j, c);
				else tmp[r][c] = a.getValue(r, c);
			}
		}
		return new Matrix2D(tmp);
	}
	
	private static Matrix2D addRow(int i, int j, double k, Matrix2D a) {
		double[][] tmp = new double[a.getRow()][a.getCol()];
		for(int r=0; r<a.getRow(); r++){
			for(int c=0; c<a.getCol(); c++){
				if(r == i)tmp[r][c] = a.getValue(r, c) + a.getValue(j, c) * k;
				else tmp[r][c] = a.getValue(r, c);
			}
		}
		return new Matrix2D(tmp);	
		
	}
	
	public static Matrix2D IdentityMatrix(int N){
		double[][] a = new double[N][N];
		for(int r=0; r<N; r++){
			for(int c=0; c<N; c++){
				if(r == c)a[r][c] = 1.0;
			}
		}
		return new Matrix2D(a);
	}
	
	
	private int rank() {
		int rank = 0;
		Matrix2D a = this;
		for(int c=0; c<a.getCol(); c++) {
			for(int r=rank; r<a.getRow(); r++) {
				if(a.getValue(r, c) == 0) continue;
				else {
					a = Matrix2D.pivodRow(r, rank, a);
					a = Matrix2D.multRow(rank, 1/a.getValue(rank, c), a);
					for(int i=rank+1; i<a.getRow(); i++) {
						if(a.getValue(i, c) != 0) {
							a = Matrix2D.addRow(i, rank, -a.getValue(i, c), a);
						}
					}
					rank++;
					break;
				}
			}
		}
		return rank;
	}
	
	private boolean isRegular() {
		if(this.rank() == this.getRow()) return true;
		else return false;
	}
	
	private boolean isSquare() {
		if(this.getRow() == this.getCol()) return true;
		else return false;
	}
	
	public static Matrix2D inv(Matrix2D a){
		if(!a.isRegular()) {
			System.out.println("Error.Inverse matrix do not exist.");
			return(null);
		}
		Matrix2D tmp = Matrix2D.catHorizon(a, Matrix2D.IdentityMatrix(a.getRow()));
		for(int r=0; r<a.getRow(); r++){
			if(a.getValue(r, r) >= 0)continue;
			for(int i=r+1; i<a.getRow(); i++){
				if(a.getValue(i, r) >= 0){
					tmp = Matrix2D.pivodRow(r, i, tmp);
					break;
				}
			}
		}
	
		for(int r=0; r<a.getCol(); r++){
			double A = 1.0 / tmp.getValue(r, r);
			for(int c=0; c<tmp.getCol(); c++){
				tmp.changeValue(r, c, A*tmp.getValue(r, c));
			}
	
			for(int i=r+1; i<a.getRow(); i++){
				double B = tmp.getValue(i, r);
				for(int c=0; c<tmp.getCol(); c++){
				tmp.changeValue(i, c, tmp.getValue(i, c)-B*tmp.getValue(r, c));				}
			}
		}
	
		for(int r=0; r<a.getRow(); r++){
			for(int i=r+1; i<a.getCol(); i++){
				double B = tmp.getValue(r, i);
				for(int j=i; j<tmp.getCol(); j++){
					tmp.changeValue(r, j, tmp.getValue(r, j) - B*tmp.getValue(i, j));
				}
			}
		}
		double[][] e = new double[a.getRow()][a.getCol()];
		for(int r=0; r<a.getRow(); r++){
			for(int c=0; c<a.getCol(); c++){
				e[r][c] = tmp.getValue(r, c+a.getCol());
			}
		}
		return new Matrix2D(e);
	}


	
	@Override
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int r=0; r<getRow(); r++){
			sb.append("|");
			for(int c=0; c<getCol(); c++){
				if(matrix[r][c] < 0)sb.append(String.format("%.5f ", matrix[r][c]));
				else sb.append(String.format(" %.5f ", matrix[r][c]));
			}
			sb.append("|\n");
		}
		return sb.toString();
	}

}

