package generic

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

/*
*
类型约束
*/
type Number interface {
	int64 | float64
}

func SumIntOrFloats[K comparable, V int64 | float64](m map[K]V) V {
	var s V
	for _, v := range m {
		s += v
	}
	return s
}

func SumNumber[K comparable, V Number](m map[K]V) V {
	var s V
	for _, v := range m {
		s += v
	}
	return s
}

func TestUsage1(t *testing.T) {
	m := map[int64]int64{1: 1, 2: 2, 3: 3}
	assert.Equal(t, int64(6), SumIntOrFloats(m))
	assert.Equal(t, int64(6), SumNumber(m))
}
