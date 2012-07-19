package ui;

import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import queries.Queries;
import disc.Disc;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import CC_Library.CC_Test;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class Comp_Main extends Composite {
	private Text txtTitle;
	private Text txtArtist;
	private Table table;
	private Text txtDisc;
	private Text txtTrack;
	private ArrayList<Disc> alDiscs = new ArrayList<Disc>();

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public Comp_Main(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		CLabel lblDisc = new CLabel(this, SWT.NONE);
		lblDisc.setSize(29, 19);
		lblDisc.setText("Disc:");

		txtDisc = new Text(this, SWT.BORDER);
		txtDisc.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				queryTable();
			}
		});
		txtDisc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));
		txtDisc.setSize(588, 19);

		CLabel lblTrack = new CLabel(this, SWT.NONE);
		lblTrack.setSize(36, 19);
		lblTrack.setText("Track:");

		txtTrack = new Text(this, SWT.BORDER);
		txtTrack.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				queryTable();
			}
		});
		txtTrack.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));
		txtTrack.setSize(500, 19);

		CLabel lblTitle = new CLabel(this, SWT.NONE);
		lblTitle.setSize(30, 19);
		lblTitle.setText("Title:");

		txtTitle = new Text(this, SWT.BORDER);
		txtTitle.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				queryTable();
			}
		});
		txtTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));
		txtTitle.setSize(500, 19);

		CLabel lblArtist = new CLabel(this, SWT.NONE);
		lblArtist.setSize(36, 19);
		lblArtist.setText("Artist:");

		txtArtist = new Text(this, SWT.BORDER);
		txtArtist.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				queryTable();
			}
		});
		txtArtist.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		txtArtist.setSize(500, 19);
		
				Button btnSearch = new Button(this, SWT.NONE);
				btnSearch.setSize(45, 23);
				btnSearch.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						queryTable();
					}
				});
				btnSearch.setText("Search");
		
		Button btnClear = new Button(this, SWT.NONE);
		btnClear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtDisc.setText("");
				txtTrack.setText("");
				txtTitle.setText("");
				txtArtist.setText("");
			}
		});
		btnClear.setText("Clear");

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(final MouseEvent event) {
				if (event.button == 3) {
					Menu menu = new Menu(table.getShell(), SWT.POP_UP);

					MenuItem mntmEdit = new MenuItem(menu, SWT.PUSH);
					mntmEdit.setText("Edit");

					mntmEdit.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							Point pt = new Point(event.x, event.y);
							Disc d;
							try {
								TableItem tItem = table.getItem(pt);
								d = new Disc((Integer) tItem.getData());
								Comp_Edit_Song edit_song = new Comp_Edit_Song(Main.getScrolledComposite(), SWT.FILL, d);
								Main.getScrolledComposite().setContent(edit_song);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});

					MenuItem mntmDelete = new MenuItem(menu, SWT.PUSH);
					mntmDelete.setText("Delete");

					mntmDelete.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							Point pt = new Point(event.x, event.y);
							Disc d;
							try {
								// Custom button text
								Object[] options = { "Yes", "No" };
								Component frame = null;
								int n = JOptionPane
										.showOptionDialog(
												frame,
												"Are you sure you want to Delete this?",
												"Are you sure you want to Delete this?",
												JOptionPane.YES_NO_OPTION,
												JOptionPane.QUESTION_MESSAGE,
												null, options, options[1]);
								if (n == 0) {
									TableItem tItem = table.getItem(pt);
									d = new Disc((Integer) tItem.getData());
									d.deleteDisc();
									tItem.dispose();
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});

					// draws pop up menu:
					Point pt = new Point(event.x, event.y);
					pt = table.toDisplay(pt);
					menu.setLocation(pt.x, pt.y);
					menu.setVisible(true);
				}
			}
		});
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_table.widthHint = 381;
		table.setLayoutData(gd_table);
		table.setSize(632, 352);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnDisc = new TableColumn(table, SWT.NONE);
		tblclmnDisc.setWidth(50);
		tblclmnDisc.setText("Disc");

		TableColumn tblclmnTrack = new TableColumn(table, SWT.NONE);
		tblclmnTrack.setWidth(50);
		tblclmnTrack.setText("Track");

		TableColumn tblclmnSong = new TableColumn(table, SWT.NONE);
		tblclmnSong.setWidth(200);
		tblclmnSong.setText("Song");

		TableColumn tblclmnArtist = new TableColumn(table, SWT.NONE);
		tblclmnArtist.setWidth(200);
		tblclmnArtist.setText("Artist");

		TableColumn tblclmnFormat = new TableColumn(table, SWT.NONE);
		tblclmnFormat.setWidth(100);
		tblclmnFormat.setText("Format");

		try {
			alDiscs = Queries.getDiscList();
			drawTable(table, alDiscs);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void populateItem(final TableItem item, final Disc d)
			throws Exception {
		int c = 0;
		item.setData(d.getIntDiscID());
		item.setText(c++, d.getStrDisc());
		item.setText(c++, d.getStrTrack());
		item.setText(c++, d.getStrTitle());
		item.setText(c++, d.getStrArtist());
		item.setText(c++, d.getStrFormat());
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

	public void queryTable() {
		try {
			alDiscs = Queries
					.searchDiscList(txtDisc.getText(), txtTrack.getText(),
							txtTitle.getText(), txtArtist.getText());
			drawTable(table, alDiscs);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
