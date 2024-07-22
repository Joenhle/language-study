def log_decorator(func):
    def tmp():
        print("log start")
        func()
        print("log end")

    return tmp

@log_decorator
def say_hello():
    print("hello")

say_hello()