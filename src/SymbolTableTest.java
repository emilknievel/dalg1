import java.io.*;

class SymbolTableTest {

	static void printMenu() {
		final String newline = System.getProperty("line.separator");

		String strMenu = "+--- Hash tables ---" + newline + "r : Reset all"
		+ newline;
		strMenu = strMenu + "H : Hash" + newline + "l : Lookup" + newline
		+ "i : Insert" + newline;
		strMenu = strMenu + "d : Delete" + newline + "D : Dump hashtable"
		+ newline;
		strMenu = strMenu + "q : Quit program" + newline + "h : show this text"
		+ newline;
		System.out.print(strMenu);
	}

	static char getchar() throws IOException {
		final BufferedReader myIn = new BufferedReader(new InputStreamReader(
				System.in));
		return (char) myIn.read();
	}

	public static String getLine() throws IOException {
		final BufferedReader myIn = new BufferedReader(new InputStreamReader(
				System.in));
		return myIn.readLine();
	}

	public static void main(final String[] args) throws IOException {
		char c;
		String str;

		if (args.length != 0) { // check command line flag "-t"
			System.out.println("Usage: java SymbolTest");
		} else { // no flag "-t"

			SymbolTable st = new SymbolTable();

			printMenu();

			while (true) {
				System.out.print("lab > ");
				c = getchar();
				switch (c) {
				case 'r':
					st = new SymbolTable();
					break;
				case 'H':
					System.out.print("Hash string: ");
					str = getLine();
					System.out.print("hash(" + str + ") => ");
					System.out.println(Integer.toString(st.hash(str)));
					break;
				case 'l':
					System.out.print("Lookup string: ");
					str = getLine();
					System.out.print("lookup(" + str + ") => ");
					System.out.println(st.get(str));
					break;
				case 'i':
					System.out.print("Insert string: ");
					str = getLine();
					System.out.print("with type: ");
					st.put(str, getchar());
					break;
				case 'd':
					System.out.print("Delete string: ");
					str = getLine();
					st.delete(str);
					break;
				case 'D':
					st.dump();
					break;
				case 'q':
					System.out.println("Program terminated.");
					System.exit(0);
					break;
				case 'h':
					printMenu();
					break;
				default:
					System.out.print("**** Sorry, No menu item named '");
					System.out.println(c + "'");
				}
			}
		}
	}
}
