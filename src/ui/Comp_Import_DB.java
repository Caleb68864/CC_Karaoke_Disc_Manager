package ui;

import java.awt.Component;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import CC_Library.CC_Files;
import CC_Library.CC_Numeric;

public class Comp_Import_DB extends Composite {
	private Text txtCode;
	private Text txtPath;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @param bool
	 */
	public Comp_Import_DB(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));

		CLabel lblWarning1 = new CLabel(this, SWT.NONE);
		lblWarning1.setFont(SWTResourceManager.getFont("Tahoma", 15, SWT.BOLD));
		lblWarning1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				false, 3, 1));
		lblWarning1.setText("!!! WARNING !!!");

		CLabel lblWarning2 = new CLabel(this, SWT.NONE);
		lblWarning2.setFont(SWTResourceManager.getFont("Tahoma", 9, SWT.BOLD));
		lblWarning2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				false, 3, 1));
		lblWarning2
				.setText("THIS ACTION WILL ERASE ALL DATA IN THE CURRENT DATABASE");

		CLabel lblInstruc = new CLabel(this, SWT.NONE);
		lblInstruc.setFont(SWTResourceManager.getFont("Tahoma", 9, SWT.NORMAL));
		lblInstruc.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 3, 1));
		lblInstruc
				.setText("To Proceed type the code below into the text box wtih hashes.");

		final CLabel lblCode = new CLabel(this, SWT.NONE);
		lblCode.setFont(SWTResourceManager.getFont("Tahoma", 9, SWT.NORMAL));
		lblCode.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 3, 1));
		lblCode.setText(genCode());

		txtCode = new Text(this, SWT.BORDER | SWT.CENTER);
		txtCode.setFont(SWTResourceManager.getFont("Tahoma", 9, SWT.NORMAL));
		GridData gd_txtCode = new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 3, 1);
		gd_txtCode.widthHint = 150;
		txtCode.setLayoutData(gd_txtCode);

		CLabel lblFilePath = new CLabel(this, SWT.NONE);
		lblFilePath.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblFilePath.setText("File Path:");
		
				txtPath = new Text(this, SWT.BORDER);
				txtPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
						2, 1));
				txtPath.setText(System.getProperty("user.dir")+"\\karaoke.sql");
		
				Button btnBrowse = new Button(this, SWT.NONE);
				btnBrowse.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						String path = "";
						path = CC_Files.fileChooser();
						if(path.isEmpty()){
							path = System.getProperty("user.dir");
						}
						txtPath.setText(path + "\\karaoke.sql");
					}
				});
				GridData gd_btnBrowse = new GridData(SWT.CENTER, SWT.CENTER, true,
						false, 1, 1);
				gd_btnBrowse.widthHint = 115;
				btnBrowse.setLayoutData(gd_btnBrowse);
				btnBrowse.setText("Browse");
		
		Button btnImportDatabase = new Button(this, SWT.NONE);
		btnImportDatabase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (lblCode.getText().equalsIgnoreCase(txtCode.getText())) {

					Component frame = null;
					int n = JOptionPane.showConfirmDialog(frame,
							"THIS ACTION CONNOT BE UNDONE",
							"Are You Sure You Wish To Continue",
							JOptionPane.WARNING_MESSAGE,
							JOptionPane.YES_NO_OPTION);

					if (n == 0) {

						try {
							String strSQL = "";
							strSQL = CC_Files.readTextFile(txtPath.getText());
							// CC_Test.MsgBox(strSQL);
							Main.getDB().Connect();
							Main.getDB().executeQuery(strSQL);
							Main.getDB().Disconnect();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						Comp_Main main = new Comp_Main(Main
								.getScrolledComposite(), SWT.FILL);
						Main.getScrolledComposite().setContent(main);
					} else {
						Comp_Main main = new Comp_Main(Main
								.getScrolledComposite(), SWT.FILL);
						Main.getScrolledComposite().setContent(main);
					}
				} else {
					txtCode.setBackground(SWTResourceManager
							.getColor(255, 0, 0));
					txtCode.setForeground(SWTResourceManager.getColor(255, 255,
							255));
				}
			}
		});
		GridData gd_btnImportDatabase = new GridData(SWT.CENTER, SWT.CENTER,
				true, false, 1, 1);
		gd_btnImportDatabase.widthHint = 115;
		btnImportDatabase.setLayoutData(gd_btnImportDatabase);
		btnImportDatabase.setText("Import Database");
		
				Button btnCancel = new Button(this, SWT.NONE);
				btnCancel.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Comp_Main main = new Comp_Main(Main.getScrolledComposite(),
								SWT.FILL);
						Main.getScrolledComposite().setContent(main);
					}
				});
				GridData gd_btnCancel = new GridData(SWT.CENTER, SWT.CENTER, true,
						false, 1, 1);
				gd_btnCancel.widthHint = 115;
				btnCancel.setLayoutData(gd_btnCancel);
				btnCancel.setText("Cancel");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	private String genCode() {
		String strCode = "";
		int intSet1 = 0;
		int intSet2 = 0;
		int intSet3 = 0;
		intSet1 = (int) Math.round(Math.random() * 1000);
		intSet2 = (int) Math.round(Math.random() * 1000);
		intSet3 = (int) Math.round(Math.random() * 1000);
		strCode = CC_Numeric.Num0fCharacters(intSet1, 3) + "-"
				+ CC_Numeric.Num0fCharacters(intSet2, 3) + "-"
				+ CC_Numeric.Num0fCharacters(intSet3, 3);
		return strCode;
	}

}
