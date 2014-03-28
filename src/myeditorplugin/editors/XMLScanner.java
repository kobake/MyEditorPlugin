package myeditorplugin.editors;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

public class XMLScanner extends RuleBasedScanner {

	private ColorManager m_manager;
	
	public XMLScanner(ColorManager manager) {
		m_manager = manager;
		initialize();
	}
	public void initialize(){
		/*
		IToken procInstr =
			new Token(
				new TextAttribute(
					manager.getColor(IXMLColorConstants.PROC_INSTR)));
		 */
		IToken procInstr =
				new Token(
					new TextAttribute(
						m_manager.getColor(PreferenceConstants.COLOR_PROC)));
		IRule[] rules = new IRule[2];
		//Add rule for processing instructions
		rules[0] = new SingleLineRule("<?", "?>", procInstr);
		// Add generic whitespace rule.
		rules[1] = new WhitespaceRule(new XMLWhitespaceDetector());

		setRules(rules);
	}
}
