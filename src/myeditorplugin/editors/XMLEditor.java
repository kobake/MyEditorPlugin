package myeditorplugin.editors;

import myeditorplugin.Activator;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.editors.text.TextEditor;

public class XMLEditor extends TextEditor 
	implements IPropertyChangeListener{

	private ColorManager colorManager;

	public XMLEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
		// 設定変更検知
		Activator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
		// リスナ削除
		Activator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// 設定が変更された
		((XMLConfiguration)getSourceViewerConfiguration()).updatePreferences();
		// XMLConfiguration config = (XMLConfiguration)getSourceViewerConfiguration();
		// config.updatePreferences();
		
		// テキストエディタ再描画
		getSourceViewer().invalidateTextPresentation();
	}

}
