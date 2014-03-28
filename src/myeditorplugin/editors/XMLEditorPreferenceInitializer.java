package myeditorplugin.editors;

import myeditorplugin.Activator;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;

public class XMLEditorPreferenceInitializer extends
		AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		// TODO Auto-generated method stub
		
		IPreferenceStore store = Activator.getDefault()
				.getPreferenceStore();
		store.setDefault(PreferenceConstants.COLOR_TAG,
				StringConverter.asString(IXMLColorConstants.TAG));
		store.setDefault(PreferenceConstants.COLOR_COMMENT,
				StringConverter.asString(IXMLColorConstants.XML_COMMENT));
		store.setDefault(PreferenceConstants.COLOR_STRING,
				StringConverter.asString(IXMLColorConstants.STRING));
		store.setDefault(PreferenceConstants.COLOR_PROC,
				StringConverter.asString(IXMLColorConstants.PROC_INSTR));
		store.setDefault(PreferenceConstants.COLOR_DEFAULT,
				StringConverter.asString(IXMLColorConstants.DEFAULT));
	}

}

class PreferenceConstants {
    public static final String COLOR_TAG = "XMLEditor.Color.Tag";
    public static final String COLOR_COMMENT = "XMLEditor.Color.Comment";
    public static final String COLOR_STRING = "XMLEditor.Color.String";
    public static final String COLOR_PROC = "XMLEditor.Color.Proc";
    public static final String COLOR_DEFAULT = "XMLEditor.Color.Default";
}

