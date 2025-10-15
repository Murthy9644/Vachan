import os, sys, colorama

acknowledge = '''
    python "pipe.py" <flag>

    flags:
        -ac = compile App.java
'''

codestrings = {
    "appcompile": 'javac -d ../bin --module-path "D:/Java/JavaFX/javafx-sdk-21.0.8/lib" --add-modules javafx.controls src/App.java',
    "apprun": 'cd ../bin && java --module-path "D:/Java/JavaFX/javafx-sdk-21.0.8/lib" --add-modules javafx.controls App',
    "clientcompile": 'javac -d ../bin --module-path "D:/Java/JavaFX/javafx-sdk-21.0.8/lib" --add-modules javafx.controls src/Client.java',
    "clientrun": 'cd ../bin && java --module-path "D:/Java/JavaFX/javafx-sdk-21.0.8/lib" --add-modules javafx.controls Client'
}

class CompileNRun:

    def compilePipe(self, file):
        print(colorama.Fore.RESET, end = '')
        print("compiling")

        match file:
            case "App": exitcode = os.system(codestrings["appcompile"])
            case "Client": exitcode = os.system(codestrings["clientcompile"])

        if exitcode == 0: print(colorama.Fore.GREEN + "compilation successful: exit status code 0")
        else: print(colorama.Fore.RED + f"compilation failed: exit status code {exitcode}")

    def runPipe(self, file):
        print(colorama.Fore.RESET, end = '')
        print(f"dir changing to '{os.path.abspath("../bin")}' & running '{file}':")

        match file:
            case "App": exitcode = os.system(codestrings["apprun"])
            case "Client": exitcode = os.system(codestrings["clientrun"])
            
        if exitcode == 0: print(colorama.Fore.GREEN + "execution successful: exit status code 0")
        else: print(colorama.Fore.RED + f"execution terminated: exit status code {exitcode}")

    def __init__(self):

        try:
            self.flag = sys.argv[1]

        except:
            print(colorama.Fore.LIGHTYELLOW_EX + "\nflag not specified. Compilation halted")
            print(colorama.Fore.CYAN + acknowledge)
            print(colorama.Fore.RESET, end = '')
            sys.exit()

        match self.flag:
            case "-ac": self.compilePipe("App")

            case "-ar": self.runPipe("App")

            case "-acr":
                self.compilePipe("App")
                self.runPipe("App")

            case "-ccr": 
                self.compilePipe("Client")
                self.runPipe("Client")

CompileNRun()
print(colorama.Fore.RESET, end = '')