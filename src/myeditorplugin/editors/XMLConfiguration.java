package myeditorplugin.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class XMLConfiguration extends SourceViewerConfiguration {
	private XMLDoubleClickStrategy doubleClickStrategy;
	private XMLTagScanner tagScanner;
	private XMLScanner scanner;
	private ColorManager colorManager;
	
	public void updatePreferences() {
		// デフォルトの色の設定
		tagScanner.initialize();	//←initialize()は今回新設したメソッド
		scanner.initialize();

		// コメントの色の設定
		//TextAttribute attr = new TextAttribute(colorManager.getCommentColor());
		//commentDamagerPepairer.setDefaultTextAttribute(attr);	//←setDefaultTextAttribute()は今回新設したメソッド
	}	

	public XMLConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			XMLPartitionScanner.XML_COMMENT,
			XMLPartitionScanner.XML_COMMENT2,
			XMLPartitionScanner.XML_UNKO,
			XMLPartitionScanner.XML_TAG };
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new XMLDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected XMLScanner getXMLScanner() {
		if (scanner == null) {
			scanner = new XMLScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IXMLColorConstants.DEFAULT))));
		}
		return scanner;
	}
	protected XMLTagScanner getXMLTagScanner() {
		if (tagScanner == null) {
			tagScanner = new XMLTagScanner(colorManager);
			tagScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IXMLColorConstants.TAG))));
		}
		return tagScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		// XMLTag
		DefaultDamagerRepairer dr =
			new DefaultDamagerRepairer(getXMLTagScanner());
		reconciler.setDamager(dr, XMLPartitionScanner.XML_TAG);
		reconciler.setRepairer(dr, XMLPartitionScanner.XML_TAG);

		// XML
		dr = new DefaultDamagerRepairer(getXMLScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		// COMMENT
		/*
		NonRuleBasedDamagerRepairer ndr =
			new NonRuleBasedDamagerRepairer(
					new TextAttribute(
							colorManager.getColor(IXMLColorConstants.XML_COMMENT)));
		*/
		NonRuleBasedDamagerRepairer ndr =
				new NonRuleBasedDamagerRepairer(
						new TextAttribute(
								new Color(Display.getCurrent(), 255, 0, 0)));
		reconciler.setDamager(ndr, XMLPartitionScanner.XML_COMMENT);
		reconciler.setRepairer(ndr, XMLPartitionScanner.XML_COMMENT);

		// COMMENT2
		NonRuleBasedDamagerRepairer ndr2 =
				new NonRuleBasedDamagerRepairer(
						new TextAttribute(
								colorManager.getColor(IXMLColorConstants.XML_COMMENT2)));
		reconciler.setDamager(ndr2, XMLPartitionScanner.XML_COMMENT2);
		reconciler.setRepairer(ndr2, XMLPartitionScanner.XML_COMMENT2);
		
		// UNKO
		NonRuleBasedDamagerRepairer ndru =
				new NonRuleBasedDamagerRepairer(
						new TextAttribute(
								colorManager.getColor(IXMLColorConstants.XML_UNKO)));
		reconciler.setDamager(ndru, XMLPartitionScanner.XML_UNKO);
		reconciler.setRepairer(ndru, XMLPartitionScanner.XML_UNKO);
		return reconciler;
	}

}