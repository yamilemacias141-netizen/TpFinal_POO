package estructuraTP.Utilidades;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JPanel;

public abstract class Navegador {
	public static void cambiarPantalla(JPanel origen, String nombrePantalla) {
		JPanel contenedor = obtenerCardLayout(origen);
		if (contenedor != null) {
			CardLayout cl = (CardLayout) contenedor.getLayout();
			cl.show(contenedor, nombrePantalla);
		}
	}

	private static JPanel obtenerCardLayout(Component origen) {
		JPanel padre = (JPanel) origen.getParent();
		while (padre != null && !(padre.getLayout() instanceof CardLayout)) {
			padre = (JPanel) padre.getParent();
		}
		return padre;
	}
}
