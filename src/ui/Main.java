package ui;

import java.sql.ResultSet;
import java.sql.SQLException;

import kdm.Variables;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Button;
import CC_Library.CC_H2;
import CC_Library.CC_SWT;

public class Main {

	static CC_H2 db = null;

	public static CC_H2 getDB() {
		return db;
	}
	
	protected Shell shlCcKaraokeDisc;
	private Text text;
	private Text text_1;
	private Table table;

	/**
	 * Launch the application.
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
	 * @throws SQLException 
	 */
	public void open() throws SQLException {
		Display display = Display.getDefault();
		createContents();
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
	 * @throws SQLException 
	 */
	protected void createContents() throws SQLException {
		shlCcKaraokeDisc = new Shell();
		shlCcKaraokeDisc.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent arg0) {
				getDB().ClosePool();
			}
		});
		shlCcKaraokeDisc.setSize(450, 300);
		shlCcKaraokeDisc.setText("CC Karaoke Disc Manager");
		shlCcKaraokeDisc.setLayout(new GridLayout(2, false));
		
		CLabel lblSong = new CLabel(shlCcKaraokeDisc, SWT.NONE);
		lblSong.setText("Song:");
		
		text = new Text(shlCcKaraokeDisc, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblArtist = new CLabel(shlCcKaraokeDisc, SWT.NONE);
		lblArtist.setText("Artist:");
		
		
		text_1 = new Text(shlCcKaraokeDisc, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shlCcKaraokeDisc, SWT.NONE);
		
		Button btnSearch = new Button(shlCcKaraokeDisc, SWT.NONE);
		btnSearch.setText("Search");
		
		table = new Table(shlCcKaraokeDisc, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnCode = new TableColumn(table, SWT.NONE);
		tblclmnCode.setWidth(100);
		tblclmnCode.setText("Code");
		
		TableColumn tblclmnSong = new TableColumn(table, SWT.NONE);
		tblclmnSong.setWidth(100);
		tblclmnSong.setText("Song");
		
		TableColumn tblclmnArtist = new TableColumn(table, SWT.NONE);
		tblclmnArtist.setWidth(100);
		tblclmnArtist.setText("Artist");
		
		Menu menu = new Menu(shlCcKaraokeDisc, SWT.BAR);
		shlCcKaraokeDisc.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		MenuItem mntmAddSong = new MenuItem(menu_1, SWT.NONE);
		mntmAddSong.setText("Add Song");

	}
}
