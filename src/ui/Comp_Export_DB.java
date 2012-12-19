package ui;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import queries.Queries;
import CC_Library.CC_Files;
import disc.Disc;

public class Comp_Export_DB extends Composite {
	private Text txtPath;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @param bool
	 */
	public Comp_Export_DB(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(4, false));

		CLabel lblFilePath = new CLabel(this, SWT.NONE);
		lblFilePath.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblFilePath.setText("File Path:");

		txtPath = new Text(this, SWT.BORDER);
		txtPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				3, 1));
		txtPath.setText(System.getProperty("user.dir")+"\\karaoke.sql");
		new Label(this, SWT.NONE);

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

		Button btnExportDatabase = new Button(this, SWT.NONE);
		btnExportDatabase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					ArrayList<Disc> exportAL = Queries.getDiscList();
					String strSQL = "";
					for (int i = 0; i < exportAL.size(); i++) {
					strSQL += "INSERT INTO tblDiscs (Disc_Disc,Disc_Track,Disc_Title,Disc_Artist,Disc_Format) VALUES ("
							+ "'"
							+ exportAL.get(i).getStrDisc()
							+ "',"
							+ exportAL.get(i).getStrTrack()
							+ ",'"
							+ exportAL.get(i).getStrTitle()
							+ "','"
							+ exportAL.get(i).getStrArtist()
							+ "','"
							+ exportAL.get(i).getStrFormat() + "');";
					strSQL += "COMMIT;";
					}
					//CC_Test.MsgBox(strSQL);
					CC_Files.writeTextFile(txtPath.getText(), strSQL);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Comp_Main main = new Comp_Main(Main.getScrolledComposite(),
						SWT.FILL);
				Main.getScrolledComposite().setContent(main);

			}
		});
		GridData gd_btnExportDatabase = new GridData(SWT.CENTER, SWT.CENTER,
				true, false, 1, 1);
		gd_btnExportDatabase.widthHint = 115;
		btnExportDatabase.setLayoutData(gd_btnExportDatabase);
		btnExportDatabase.setText("Export Database");

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
}
