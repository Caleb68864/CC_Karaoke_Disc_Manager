package ui;

import java.sql.SQLException;
import java.util.ArrayList;

import kdm.Variables;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import queries.Queries;

import CC_Library.CC_H2;
import disc.Disc;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Main {

	static CC_H2 db = null;

	public static CC_H2 getDB() {
		return db;
	}

	protected Shell shlCcKaraokeDisc;
	private Text txtTitle;
	private Text txtArtist;
	private Table table;
	private Text txtDisc;
	private Text txtTrack;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			db = new CC_H2(Variables.getStrConn(), Variables.getStrUser(),
					Variables.getStrPassword(), CC_H2.Embedded());

			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * 
	 * @throws SQLException
	 */
	public void open() throws SQLException {
		Display display = Display.getDefault();
		try {
			createContents();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shlCcKaraokeDisc.open();
		shlCcKaraokeDisc.layout();
		while (!shlCcKaraokeDisc.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * 
	 * @throws Exception
	 */
	protected void createContents() throws Exception {
		shlCcKaraokeDisc = new Shell();
		shlCcKaraokeDisc.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent arg0) {
				getDB().ClosePool();
			}
		});
		shlCcKaraokeDisc.setSize(450, 300);
		shlCcKaraokeDisc.setText("CC Karaoke Disc Manager");
		shlCcKaraokeDisc.setLayout(new GridLayout(2, false));

		CLabel lblDisc = new CLabel(shlCcKaraokeDisc, SWT.NONE);
		lblDisc.setText("Disc:");

		txtDisc = new Text(shlCcKaraokeDisc, SWT.BORDER);
		txtDisc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		CLabel lblTrack = new CLabel(shlCcKaraokeDisc, SWT.NONE);
		lblTrack.setText("Track:");

		txtTrack = new Text(shlCcKaraokeDisc, SWT.BORDER);
		txtTrack.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		CLabel lblSong = new CLabel(shlCcKaraokeDisc, SWT.NONE);
		lblSong.setText("Song:");

		txtTitle = new Text(shlCcKaraokeDisc, SWT.BORDER);
		txtTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		CLabel lblArtist = new CLabel(shlCcKaraokeDisc, SWT.NONE);
		lblArtist.setText("Artist:");

		txtArtist = new Text(shlCcKaraokeDisc, SWT.BORDER);
		txtArtist.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		new Label(shlCcKaraokeDisc, SWT.NONE);

		Button btnSearch = new Button(shlCcKaraokeDisc, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ArrayList<Disc> alFound = new ArrayList<Disc>();
					alFound = Queries.searchDiscList(txtDisc.getText(),
							txtTrack.getText(), txtTitle.getText(),
							txtArtist.getText());
					drawTable(table, alFound);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setText("Search");

		table = new Table(shlCcKaraokeDisc, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnDisc = new TableColumn(table, SWT.NONE);
		tblclmnDisc.setWidth(100);
		tblclmnDisc.setText("Disc");

		TableColumn tblclmnTrack = new TableColumn(table, SWT.NONE);
		tblclmnTrack.setWidth(100);
		tblclmnTrack.setText("Track");

		TableColumn tblclmnSong = new TableColumn(table, SWT.NONE);
		tblclmnSong.setWidth(100);
		tblclmnSong.setText("Song");

		TableColumn tblclmnArtist = new TableColumn(table, SWT.NONE);
		tblclmnArtist.setWidth(100);
		tblclmnArtist.setText("Artist");

		ArrayList<Disc> alDiscs = new ArrayList<Disc>();
		alDiscs = Queries.getDiscList();
		drawTable(table, alDiscs);

		Menu menu = new Menu(shlCcKaraokeDisc, SWT.BAR);
		shlCcKaraokeDisc.setMenuBar(menu);

		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");

		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);

		MenuItem mntmAddSong = new MenuItem(menu_1, SWT.NONE);
		mntmAddSong.setText("Add Song");

	}

	public void populateItem(final TableItem item, final Disc d)
			throws Exception {
		int c = 0;
		item.setText(c++, d.getStrDisc());
		item.setText(c++, d.getStrTrack());
		item.setText(c++, d.getStrTitle());
		item.setText(c++, d.getStrArtist());
	}

	public void drawTable(Table table, ArrayList<Disc> alDiscs)
			throws Exception {
		table.setRedraw(false);
		table.removeAll();
		final TableItem[] items = new TableItem[alDiscs.size()];
		for (int i = 0; i < alDiscs.size(); i++) {
			items[i] = new TableItem(table, SWT.NONE);
			final Disc d = alDiscs.get(i);
			populateItem(items[i], d);
		}

		table.setRedraw(true);
	}
}
