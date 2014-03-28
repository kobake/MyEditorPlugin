package myeditorplugin.editors;

import org.eclipse.jface.text.rules.*;

public class XMLPartitionScanner extends RuleBasedPartitionScanner {
	public final static String XML_COMMENT = "__xml_comment";
	public final static String XML_COMMENT2 = "__xml_comment2";
	public final static String XML_UNKO = "__xml_unko";
	public final static String XML_TAG = "__xml_tag";

	public XMLPartitionScanner() {

		IToken xmlComment = new Token(XML_COMMENT);
		IToken xmlComment2 = new Token(XML_COMMENT2);
		IToken xmlUnko = new Token(XML_UNKO);
		IToken tag = new Token(XML_TAG);

		IPredicateRule[] rules = new IPredicateRule[4];

		rules[0] = new MultiLineRule("<!--", "-->", xmlComment);
		rules[1] = new MultiLineRule("<<<", ">>>", xmlComment2);
		rules[2] = new PatternRule("un", "ko", xmlUnko, '\0', false);
		rules[3] = new TagRule(tag);

		setPredicateRules(rules);
	}
}
