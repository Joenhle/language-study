import asyncio

async def func(param: int):
    await asyncio.sleep(param)
    if param == 6:
        1/0
    print(param)
    return f'res:{param}'

async def do():
    tasks : list[asyncio.Task] = []
    try:
        async with asyncio.TaskGroup() as tg:
            for i in range(10):
                tasks.append(tg.create_task(func(i)))
    except Exception as e:
        for idx, task in enumerate(tasks):
            try:
                print(task.result(), task.cancelled())
            except:
                print(f'task:{idx} error', task.cancelled())


if __name__ == '__main__':
    asyncio.run(do())