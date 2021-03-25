package br.jeronimo.api.sicredi.domain.enums;

public enum Perfil {

	ASSOCIATE_ADMIN(1, "ROLE_ADMIN"),
	ASSOCIATE(2, "ROLE_ASSOCIATE");
	
	private int cod;
	private String description;
	
	private Perfil(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() 
	{
		return cod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Perfil toEnum(Integer cod) 
	{
		if(cod==null) {
		return null;
		}
		
		for(Perfil x : Perfil.values()) 
		{
			if(cod.equals(x.getCod())) 
			{
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}
