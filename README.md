# Some recommendation for reading
https://prometheus.io/docs/concepts/metric_types/

## see
example.APIController.APIController
example.APIController.foo

## all metrics are available by the link
curl http://localhost:8080/actuator/prometheus

## names of metrics
```
* metrics_example_SUCCESS_total - some descr if required
* metrics_example_WARNING_total - some descr if required
* metrics_example_ERROR_total - some descr if required
```

## example
1) run
2) execute the command bellow
```bash
for i in {1..100}
do
 curl http://localhost:8080/foo
done;

curl http://localhost:8080/actuator/prometheus;

```
3) 
ctl+f -> metrics_example_SUCCESS_total
ctl+f -> metrics_example_WARNING_total
ctl+f -> metrics_example_ERROR_total