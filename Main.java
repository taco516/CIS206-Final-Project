import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;



public class Main
{
    private String m_name;
    private long m_phoneno;
    private String m_address;
    private String m_email;
    private String m_webaddr;
  
    public Main( String name, long phoneno, String address, String email, String webaddr )
    {

        m_name = name;
        m_phoneno = phoneno;
        m_address = address;
        m_email = email;
        m_webaddr = webaddr;
    }

    public String getName()
    {
        return m_name;
    }

    public long getPhoneno()
    {
        return m_phoneno;
    }
    public String getaddress()
    {
        return m_address;
    }
    public String getEmail()
    {
        return m_email;
    }
    public String getWebaddr()
    {
        return m_webaddr;
    }
    public String toString()
    {
        return "name: " + m_name + "\nphone number: " +  m_phoneno +
               "\naddress: " +  m_address + "\nemail: " + m_email +
               "\nwebaddress: " + m_webaddr + "\n";
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {
       ArrayList<Main> contacts = new ArrayList<Main>();

       try {
            Scanner in = new Scanner (new FileReader("contacts.txt"));
            while (in.hasNext()) {
              String line = new String (in.nextLine());
              String[] data = line.split("#");
              Main contact = new Main (data[0], Long.parseUnsignedLong(data[1]), data[2], data[3], data[4]);
              contacts.add (contact);
            }
       }
       catch(Exception e)
       {

       }

       Scanner input = new Scanner(System.in);
       int menuChoice = 4;
       do 
       {
           System.out.println("Adress Book");

            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Save");
            System.out.println("5. Exit");
            
           try {

               System.out.println("Enter a choice: ");

               menuChoice = Integer.parseInt(input.nextLine());

           } catch (NumberFormatException e) {

               continue;

           }

           if (menuChoice==1)

           {

               System.out.print("Full name:");

               String name = input.nextLine();

               long phoneno = promptPhoneno();


               System.out.print("address:");

               String address = input.nextLine();

               System.out.print("email:");

               String email = input.nextLine();

               System.out.print("webaddr:");

               String webaddr = input.nextLine();

               Main contact = new Main(name, phoneno, address, email, webaddr);

               contacts.add(contact);

           } else if (menuChoice==2) {

               System.out.println("Contacts:");

               for (Main contact : contacts)

               {

                   System.out.println(contact.toString());

               }
              
           }

           else if (menuChoice ==3)
           {
            System.out.print("Enter Name To Search:");
            String name = input.nextLine();
            for (Main contact : contacts){
              if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println(contact.toString());
              }
            }
           }

            else if (menuChoice == 4)
            {
              try
              {
              FileWriter file = new FileWriter("contacts.txt",false);
                for (Main contact : contacts)
                {
                file.write (contact.getName() + "#" + contact.getPhoneno() + "#" + contact.getaddress() + "#" + contact.getEmail() + "#" + contact.getWebaddr() + "\n");
                }
              file.close();
              }
              catch(Exception e)
              {
                
              }
            }
       } while (menuChoice<5);

    }
       public static long promptPhoneno ()
       {
        Scanner input = new Scanner(System.in);
        long phoneno;
        try 
        {
          System.out.println("Phone Number:");
          phoneno = Long.parseUnsignedLong(input.nextLine());
        } 
        catch (NumberFormatException e)
        {
          return promptPhoneno();
        }
        return phoneno;
       }

}