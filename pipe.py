import os, sys, colorama

colorama.init(autoreset= True)

acknowledge = '''
    python "pipe.py" <command> <file>

    command flags:
        --build = compile file
        --exec = run file
        --fire = build & run file

    file flags:
        -m = Main.java
'''

codestrings = {
    "maincompile": 'javac -d bin ^'
    '--module-path "D:/Java-libs/JavaFX/javafx-sdk-25.0.1/lib;D:/Java-libs/external-jars" ^'
    '--add-modules javafx.controls,javafx.fxml,com.fasterxml.jackson.databind ^'
    'src/Main.java src/backend/*.java src/controllers/*.java',

    "mainrun": 'cd bin && java ^'
    '--module-path "D:/Java-libs/JavaFX/javafx-sdk-25.0.1/lib;D:/Java-libs/external-jars" ^'
    '--add-modules javafx.controls,javafx.fxml,com.fasterxml.jackson.databind ^'
    'Main',
}

class CompileNRun:

    def compilePipe(self, file):
        print(colorama.Fore.RESET, end = '')
        print("compiling...")

        match file:
            case 'm': exitcode = os.system(codestrings["maincompile"])

        if exitcode == 0: print(colorama.Fore.GREEN + "compilation successful: exit status code 0")
        else: print(colorama.Fore.RED + f"compilation failed: exit status code {exitcode}")

    def runPipe(self, file):
        print(colorama.Fore.RESET, end = '')
        print(f"navigating to '{os.path.abspath('bin')}'")
        print("runtime initiated...")

        match file:
            case 'm': exitcode = os.system(codestrings["mainrun"])
            
        if exitcode == 0: print(colorama.Fore.GREEN + "execution successful: exit status code 0")
        else: print(colorama.Fore.RED + f"execution terminated: exit status code {exitcode}")

    def interface(self, cmd, fle):

        match cmd:
            case "build": self.compilePipe(fle)
            case "exec": self.runPipe(fle)
            case "fire":
                self.compilePipe(fle)
                self.runPipe(fle)

    def __init__(self):
        command = ''

        try:
            self.command = sys.argv[1]

            try: self.file = sys.argv[2]

            except: 
                print(colorama.Fore.LIGHTYELLOW_EX + "\nfile not specified: service terminated")
                print(colorama.Fore.CYAN + acknowledge)
                print(colorama.Fore.RESET, end = '')
                sys.exit()

        except:
            print(colorama.Fore.LIGHTYELLOW_EX + "\ncommand not specified: service terminated")
            print(colorama.Fore.CYAN + acknowledge)
            print(colorama.Fore.RESET, end = '')
            sys.exit()

        command = self.command.removeprefix('--')

        if command not in ["build", "exec", "fire"]:
            print(colorama.Fore.LIGHTYELLOW_EX + "unknown command:", command)
            print(colorama.Fore.CYAN + acknowledge)
            sys.exit()

        file = self.file.removeprefix('-')

        if file not in ['m', 'sm', 'all']:
            print(colorama.Fore.LIGHTYELLOW_EX + "unknown file:", file)
            print(colorama.Fore.CYAN + acknowledge)
            sys.exit()

        self.interface(command, file)

CompileNRun()
print(colorama.Fore.RESET, end = '')