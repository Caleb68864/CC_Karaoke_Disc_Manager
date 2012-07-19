package ui;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import disc.Disc;

public class Comp_Edit_Song extends Composite {
	private Text txtTitle;
	private Text txtArtist;
	private Text txtDisc;
	private Text txtTrack;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public Comp_Edit_Song(Composite parent, int style, final Disc disc) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		CLabel lblDisc = new CLabel(this, SWT.NONE);
		lblDisc.setText("Disc:");

		txtDisc = new Text(this, SWT.BORDER);
		txtDisc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		txtDisc.setText(disc.getStrDisc());

		CLabel lblTrack = new CLabel(this, SWT.NONE);
		lblTrack.setText("Track:");

		txtTrack = new Text(this, SWT.BORDER);
		txtTrack.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		txtTrack.setText(disc.getStrTrack());

		CLabel lblTitle = new CLabel(this, SWT.NONE);
		lblTitle.setText("Title:");

		txtTitle = new Text(this, SWT.BORDER);
		txtTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		txtTitle.setText(disc.getStrTitle());

		CLabel lblArtist = new CLabel(this, SWT.NONE);
		lblArtist.setText("Artist:");

		txtArtist = new Text(this, SWT.BORDER);
		txtArtist.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		txtArtist.setText(disc.getStrArtist());

		CLabel lblFormat = new CLabel(this, SWT.NONE);
		lblFormat.setText("Format:");

		final CCombo cboFormat = new CCombo(this, SWT.BORDER);
		cboFormat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		cboFormat.add("Disc");
		cboFormat.add("Digital");
		cboFormat.setText(disc.getStrFormat());

		Button btnSave = new Button(this, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					disc.setStrDisc(txtDisc.getText());
					disc.setStrTrack(txtTrack.getText());
					disc.setStrTitle(txtTitle.getText());
					disc.setStrArtist(txtArtist.getText());
					disc.setStrFormat(cboFormat.getText());
					disc.updateDisc();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Comp_Main main = new Comp_Main(Main.getScrolledComposite(),
						SWT.FILL);
				Main.getScrolledComposite().setContent(main);
			}
		});
		btnSave.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));
		btnSave.setText("Save");

		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Comp_Main main = new Comp_Main(Main.getScrolledComposite(),
						SWT.FILL);
				Main.getScrolledComposite().setContent(main);
			}
		});
		btnCancel.setText("Cancel");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
