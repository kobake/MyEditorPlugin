package myeditorplugin.editors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import myeditorplugin.Activator;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ColorManager {

	protected Map fColorTable = new HashMap(10);
	protected IPreferenceStore m_store;
	
	public ColorManager(){
		m_store = Activator.getDefault().getPreferenceStore();
	}
	public Color getColor(String prefKey){
		String colorName = m_store.getString(prefKey);
		RGB rgb = StringConverter.asRGB(colorName);
		return getColor(rgb);
	}

	public void dispose() {
		Iterator e = fColorTable.values().iterator();
		while (e.hasNext())
			 ((Color) e.next()).dispose();
	}
	public Color getColor(RGB rgb) {
		Color color = (Color) fColorTable.get(rgb);
		if (color == null) {
			color = new Color(Display.getCurrent(), rgb);
			fColorTable.put(rgb, color);
		}
		return color;
	}
}
