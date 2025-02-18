package srcs.service.calculatrice;

public interface Calculatrice {
	public int add(int i1, int i2);
	public int sous(int i1, int i2);
	public int mult(int i1, int i2);
	public ResDiv div(int i1, int i2);
	
	public static class ResDiv{
		
		private int quotient;
		private int reste;
		public ResDiv(int i1,int i2) {
			quotient = i1 / i2;
			reste = i1 % i2;
		}

	}
	
	
	

}
