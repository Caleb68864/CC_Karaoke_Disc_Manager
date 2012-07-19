package ui;

import java.io.File;
import java.sql.ResultSet;
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
import org.eclipse.swt.widgets.Composite;
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
import CC_Library.CC_Test;
import disc.Disc;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.ScrolledComposite;

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

		scrolledComposite = new ScrolledComposite(shlCcKaraokeDisc, SWT.BORDER
				| SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Comp_Main main = new Comp_Main(scrolledComposite, SWT.FILL);
		scrolledComposite.setContent(main);
	}

	public static ScrolledComposite getScrolledComposite() {
		return scrolledComposite;
	}

	public void setScrolledComposite(ScrolledComposite scrolledComposite) {
		this.scrolledComposite = scrolledComposite;
	}
}
