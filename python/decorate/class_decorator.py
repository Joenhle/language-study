from typing import Any
import time


class CacheCounterDecorator:
    def __init__(self, name) -> None:
        self.name = name
        self.cache = {}
        self.counter = 0

    def __call__(self, func) -> Any:
        def wrapper(*args, **kwargs):
            if args not in self.cache:
                self.counter += 1
                self.cache[args] = func(*args)
            return self.cache[args]
        return wrapper

@CacheCounterDecorator(name="decorator")
def mul_self(x):
    time.sleep(1)
    return x * x

# c = CacheCounterDecorator(name="decorator")
# mul_self = c(mul_self)


print(mul_self(2))
print(mul_self(2))


