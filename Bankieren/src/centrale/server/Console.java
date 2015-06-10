/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrale.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Cypher
 */
public class Console {

    public JFrame frame;
    public JTextPane console;
    public JTextField input;
    public JScrollPane scrollpane;
    
    public StyledDocument document;
    
    public boolean trace = false;
    
    ArrayList<String> recent_commands = new ArrayList<String>();
    int recent_command_id = 0;
    int recent_command_max = 10;
    
    private BankCentrale server;
    
    public Console()
    {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex){}
        
        frame = new JFrame();
        frame.setTitle("Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        console = new JTextPane();
        console.setEditable(false);
        console.setFont(new Font("Consolas", Font.BOLD, 12));
        console.setOpaque(false);
        
        document = console.getStyledDocument();
        
        input = new JTextField();
        input.setEditable(true);
        input.setFont(new Font("Consolas", Font.BOLD, 12));
        input.setForeground(Color.WHITE);
        input.setCaretColor(Color.GREEN);
        input.setOpaque(false);
        
        input.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                String text = input.getText();
                
                if (text.length() > 1)
                {
                    recent_commands.add(text);
                    recent_command_id = 0;
                    
                    doCommand(text);
                    scrollBottom();
                    input.selectAll();
                }
            }
            
        });
        
        input.addKeyListener(new KeyListener()
        {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    if(recent_command_id < (recent_command_max - 1) && recent_command_id < (recent_commands.size()-1))
                        recent_command_id++;
                    
                    input.setText(recent_commands.get(recent_commands.size() - 1 - recent_command_id));
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    if(recent_command_id > 0)
                        recent_command_id--;
                    
                    input.setText(recent_commands.get(recent_commands.size() - 1 - recent_command_id));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        
        scrollpane = new JScrollPane(console);
        scrollpane.setBorder(null);
        scrollpane.setOpaque(false);
        scrollpane.getViewport().setOpaque(false);
        
        frame.add(input, BorderLayout.SOUTH);
        frame.add(scrollpane, BorderLayout.CENTER);
        
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        
        frame.setSize(768, 512);
        frame.setLocationRelativeTo(null);
        
        frame.setResizable(false);
        frame.setVisible(true);
                
    }
    
    public void scrollTop()
    {
        console.setCaretPosition(0);
    }
    
    public void scrollBottom()
    {
        console.setCaretPosition(console.getDocument().getLength());
    }
    
    public void print(String s)
    {
        print(s, trace, new Color(255, 255, 255));
    }
    
    public void print(String s, Color c)
    {
        print(s, trace, c);
    }
    
    public void print(String s, boolean trace)
    {
        print(s, trace, new Color(255, 255, 255));
    }
    
    public void print(String s, boolean trace, Color c)
    {
        Style style = console.addStyle("Style", null);
        StyleConstants.setForeground(style, c);
        
        if (trace)
        {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String caller = elements[0].getClassName();
            
            s = caller + " -> " + s;
        }
        
        try
        {
            document.insertString(document.getLength(), s, style);
        }
        catch (Exception ex) {}
    }
    
    public void println(String s)
    {
        println(s, trace, new Color(255, 255, 255));
    }
    
    public void println(String s, Color c)
    {
        println(s, trace, c);
    }
    
    public void println(String s, boolean trace)
    {
        println(s, trace, new Color(255, 255, 255));
    }
    
    public void println(String s, boolean trace, Color c)
    {
        print(s + "\n", trace, c);
    }
    
    public void printError(String s)
    {
        printError(s, null, trace);
    }
    
    public void printError(String s, StackTraceElement[] stack, boolean trace)
    {
        println("Error -> " + s, trace, new Color(215, 50, 50));
        for(StackTraceElement e : stack)
        {
            println("    -> " + e.toString(), trace, new Color(215, 50, 50));
        }
    }
    
    public void clear()
    {
        try
        {
            document.remove(0, document.getLength());
        }
        catch(Exception ex){}
    }
   
    public void doCommand(String s)
    {
        final String[] commands = s.split(" ");
        
        try
        {
            if(commands[0].equalsIgnoreCase("list") && commands[1].equalsIgnoreCase("banks"))
                server.listBanks();
            else if(commands[0].equalsIgnoreCase("clear"))
                clear();
            else if(commands[0].equalsIgnoreCase("trace"))
            {
                if(commands.length > 1)
                {
                    boolean old = trace;
                    trace = Boolean.parseBoolean(commands[1]);
                    if(old != trace)
                        println("Trace value set to: " + String.valueOf(trace));
                    else
                        println("Trace value is allready set to: " + String.valueOf(trace));
                }
                else
                    printError("Please enter the value true or false");
            }
            else
                println(s, trace, new Color(255, 255, 255));
        }
        catch(Exception ex)
        {
            printError(ex.toString(), ex.getStackTrace(), trace);
        }
    }
    
    public void setServer(BankCentrale c)
    {
        this.server = c;
    }
}
