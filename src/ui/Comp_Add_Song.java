package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class Comp_Add_Song extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Comp_Add_Song(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		CLabel lblTitle = new CLabel(this, SWT.NONE);
		lblTitle.setText("Title:");
		
		text = new Text(this, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblArtist = new CLabel(this, SWT.NONE);
		lblArtist.setText("Artist:");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblDisc = new CLabel(this, SWT.NONE);
		lblDisc.setText("Disc:");
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblTrack = new CLabel(this, SWT.NONE);
		lblTrack.setText("Track:");
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSave = new Button(this, SWT.NONE);
		btnSave.setText("Save");
		
		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.setText("Cancel");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
