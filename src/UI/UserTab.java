package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserTab extends JPanel {
    private JTable allUsersTable;

    public UserTab(){
        this.add(BookPanel);

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBook();
            }
        });

        borrowBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrowBook();
            }
        });

        allBooksTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    var transactionID = allBooksTable.getValueAt(allBooksTable.getSelectedRow(), 0).toString();

                    LoadRating(Integer.parseInt(transactionID));

                }catch(Exception ex){
                    JOptionPane.showMessageDialog(BookPanel, ex);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        LoadBooks();

    }

}
