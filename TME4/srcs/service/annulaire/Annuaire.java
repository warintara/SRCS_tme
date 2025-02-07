package srcs.service.annulaire;

public interface Annuaire {
	public Object lookup(String nom);
	public void bind(Object val, String nom);
	public void unbind(String nom);
}
