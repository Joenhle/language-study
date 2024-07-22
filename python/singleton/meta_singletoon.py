
from typing import Any


class Singleton(type):
    def __init__(self, *args, **kwargs):
        print("2")
        self.__instance = None
        super().__init__(*args, **kwargs)
    
    def __call__(self, *args: Any, **kwds: Any) -> Any:
        print("1")
        if self.__instance is None:
            self.__instance = super().__call__(*args, **kwds)
            print(self.__instance)
            return self.__instance
        else:
            return self.__instance

class A(metaclass = Singleton):
    def __init__(self) -> None:
        print("3")

a = A()
a = A()
