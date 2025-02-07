package srcs.service.calculatrice;

public interface Calculatrice {
	public int add(int i1, int i2);
	public int sous(int i1, int i2);
	public int mult(int i1, int i2);
	public ResDiv div(int i1, int i2);
	
	static class ResDiv{
		public int getQuotient(int i1,int i2) {
			return i1/i2;
			
		}
		public int getReste(int i1,int i2) {
			return i1%i2;
			
		}
	}
	

}
