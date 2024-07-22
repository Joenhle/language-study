def repeat(n):
    def decoder(func):
        def wapper(*args, **kwargs):
            for _ in range(n):
                func(*args, **kwargs)
        return wapper
    return decoder

@repeat(3)
def say_hello(name):
    print(f"hello {name}")

say_hello("hjh")
# say_hello = repeat(3)(say_hello)("hjh")