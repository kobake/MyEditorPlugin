package myeditorplugin.editors;

import myeditorplugin.Activator;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class XMLEditorPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	public XMLEditorPreferencePage() {
		// TODO Auto-generated constructor stub
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	/*
	クラス名					説明
	BooleanFieldEditor		チェックボックスで真偽値を入力
	IntegerFieldEditor		テキストフィールドで数値を入力
	StringFieldEditor		テキストフィールドで文字列を入力
	RadioGroupFieldEditor	ラジオボタンで項目を選択
	ColorFieldEditor		色を選択
	FontFieldEditor			フォントを選択
	DirectoryFieldEditor	ファイルシステム上のディレクトリを選択
	FileFieldEditor			ファイルシステム上のファイルを選択
	PathEditor				ファイルシステム上の複数のパスを選択
	*/
	@Override
	protected void createFieldEditors() {
		// TODO Auto-generated method stub
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_TAG, "タグの色",
				getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_COMMENT,
				"コメントの色", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_STRING,
				"文字列の色", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_PROC,
				"XML宣言の色", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_DEFAULT,
				"デフォルトの色", getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

}
