package tiposDeEnemigo;

public class Jefe extends TipoEnemigoBasico{
	

	public Jefe(double JFxPos, double JFyPos, int JFwidth, int JFheight, String ImgRuta) {
		super(JFxPos,JFyPos,JFwidth,JFheight);
		super.cambiarDireccion(2);  
		
		
	}
	
}
