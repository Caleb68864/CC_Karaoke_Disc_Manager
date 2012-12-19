package ui;

import java.sql.SQLException;

import kdm.Variables;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import CC_Library.CC_H2;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

import com.xuggle.xuggler.demos.DecodeAndPlayVideo;

public class Main {

	static CC_H2 db = null;

	public static CC_H2 getDB() {
		return db;
	}

	protected Shell shlCcKaraokeDisc;

	private static ScrolledComposite scrolledComposite;

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
		shlCcKaraokeDisc.setSize(650, 550);
		shlCcKaraokeDisc.setText("CC Karaoke Disc Manager");
		shlCcKaraokeDisc.setLayout(new FillLayout(SWT.HORIZONTAL));

		Menu menu = new Menu(shlCcKaraokeDisc, SWT.BAR);
		shlCcKaraokeDisc.setMenuBar(menu);

		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");

		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);

		MenuItem mntmAddSong = new MenuItem(menu_1, SWT.NONE);
		mntmAddSong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Comp_Add_Song add_song = new Comp_Add_Song(scrolledComposite,
						SWT.FILL);
				scrolledComposite.setContent(add_song);
				// CC_Test.MsgBox("Yay");
			}
		});
		mntmAddSong.setText("Add Song");
		
		MenuItem mntmExport = new MenuItem(menu_1, SWT.NONE);
		mntmExport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Comp_Export_DB export_db = new Comp_Export_DB(scrolledComposite,
						SWT.FILL);
				scrolledComposite.setContent(export_db);
			}
		});
		mntmExport.setText("Export Database");
		
		MenuItem mntmImport = new MenuItem(menu_1, SWT.NONE);
		mntmImport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Comp_Import_DB import_db = new Comp_Import_DB(scrolledComposite,
						SWT.FILL);
				scrolledComposite.setContent(import_db);
			}
		});
		mntmImport.setText("Import Database");
		
		MenuItem mntmInstallDB = new MenuItem(menu_1, SWT.NONE);
		mntmInstallDB.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Comp_Install_DB install_db = new Comp_Install_DB(scrolledComposite,
						SWT.FILL);
				scrolledComposite.setContent(install_db);
			}
		});
		mntmInstallDB.setText("Install Database");

		scrolledComposite = new ScrolledComposite(shlCcKaraokeDisc, SWT.BORDER
				| SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Comp_Main main = new Comp_Main(scrolledComposite, SWT.FILL);
		new Label(main, SWT.NONE);
	
		scrolledComposite.setContent(main);
	}

	public static ScrolledComposite getScrolledComposite() {
		return scrolledComposite;
	}

	@SuppressWarnings("static-access")
	public void setScrolledComposite(ScrolledComposite scrolledComposite) {
		this.scrolledComposite = scrolledComposite;
	}
}
