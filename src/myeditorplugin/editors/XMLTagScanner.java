package myeditorplugin.editors;

import org.eclipse.jface.text.*;
import org.eclipse.jface.text.rules.*;

public class XMLTagScanner extends RuleBasedScanner {

	public XMLTagScanner(ColorManager manager) {
		IToken string =
			new Token(
				new TextAttribute(manager.getColor(IXMLColorConstants.STRING)));

		IRule[] rules = new IRule[4];

		// Add rule for double quotes
		rules[0] = new SingleLineRule("\"", "\"", string, '\\');
		// Add a rule for single quotes
		rules[1] = new SingleLineRule("'", "'", string, '\\');
		// 独自リテラル
		rules[2] = new SingleLineRule("[", "]", string, '\\');
		// Add generic whitespace rule.
		rules[3] = new WhitespaceRule(new XMLWhitespaceDetector());

		setRules(rules);
	}
}
