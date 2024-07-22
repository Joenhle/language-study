package reflect

import (
	"fmt"
	"os"
	"reflect"
	"testing"
)

func TestDemo1(t *testing.T) {
	type Config struct {
		Name string `tag_hjh:"name"`
		Ip   string `tag_hjh:"ip"`
	}

	readConfig := func() *Config {
		config := Config{}

		typ := reflect.TypeOf(config)
		value := reflect.ValueOf(&config)
		item := reflect.Indirect(value)

		for i := 0; i < typ.NumField(); i++ {
			f := typ.Field(i)
			if v, ok := f.Tag.Lookup("tag_hjh"); ok {
				if env, exist := os.LookupEnv(v); exist {
					item.FieldByName(f.Name).Set(reflect.ValueOf(env))
				}
			}
		}
		return &config
	}

	os.Setenv("name", "global_server")
	os.Setenv("ip", "10.0.0.1")
	c := readConfig()
	fmt.Printf("%+v", c)

}
